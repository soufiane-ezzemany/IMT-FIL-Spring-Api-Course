package imt.fil.cl.leja.songmanagerapi.singer;

import imt.fil.cl.leja.songmanagerapi.singer.projections.SingerInfoOnly;
import imt.fil.cl.leja.songmanagerapi.song.SongDTO;
import imt.fil.cl.leja.songmanagerapi.song.SongService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/singers")
public class SingerController {
    private final SingerService singerService;

    private final SongService songService;

    @Autowired
    public SingerController(SingerService singerService, SongService songService) {
        this.singerService = singerService;
        this.songService = songService;
    }

    @PostMapping(value="/add")
    @Operation(summary = "Ajouter un chanteur")
    public ResponseEntity<String> addSinger(
            @RequestBody SingerDTO singer) {

        try {
            // On utilise le service pour ajouter le chanteur à la base de données
            singerService.addSinger(singer);
            return ResponseEntity.ok("Chanteur ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout du chanteur");
        }
    }
    @PostMapping("/{singerId}/add-songs")
    public ResponseEntity<Singer> addSongsToSinger(@PathVariable Long singerId, @RequestBody Set<SongDTO> songs){
        try {
            Singer singer = singerService.getSingerById(singerId);
            songService.addSongsToSinger(singer, songs);
            return ResponseEntity.ok(singer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
    
    @GetMapping("")
    @Operation(summary = "Récupérer la liste de tout les chanteurs")
    public Optional<List<SingerInfoOnly>> getAllSingers(){
        Optional<List<SingerInfoOnly>> singers = singerService.getAllSingers();
        if (singers.isPresent()){
            return singers;
        }
        return null;
    }
}


