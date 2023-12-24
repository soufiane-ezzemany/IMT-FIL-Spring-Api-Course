package imt.fil.cl.leja.songmanagerapi.singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/singers")
public class SingerController {

    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }


    //On crée un point d'entrée qui va nous permettre d'ajouter un chanteur en passant en paramètre un objet de type Singer
    @PostMapping(value="/add", name = "Add a singer")
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
}


