package imt.fil.cl.leja.songmanagerapi.singer;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/singers")
public class SingerController {
    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @PostMapping(value="/add")
    @Operation(summary = "Ajouter un chanteur")
    public ResponseEntity<String> addSinger(
            @RequestBody Singer singer) {

        try {
            // On utilise le service pour ajouter le chanteur à la base de données
            singerService.addSinger(singer.getFirstname(), singer.getLastname());
            return ResponseEntity.ok("Chanteur ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout du chanteur");
        }
    }

    @GetMapping("{singerId}/bestsongs")
    @Operation(summary = "Récupérer un chanteur avec ses meilleurs chansons")
    public ResponseEntity<Singer> getSingerWithBestSongs(@PathVariable Long singerId) {
       return singerService.getSingerAndBestSongs(singerId)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound()
                                       .header("Error", "No singer found")
                                       .build()
               );
    }
}


