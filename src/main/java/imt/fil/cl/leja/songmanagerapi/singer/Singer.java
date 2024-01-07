package imt.fil.cl.leja.songmanagerapi.singer;

import imt.fil.cl.leja.songmanagerapi.song.Song;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Singer")
public class Singer {
    @Id
    // Génération de la clé lors d'une insertion en base de données.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "singer_id")
    @lombok.NonNull
    private Long id;
    @lombok.NonNull
    private String firstname;
    @lombok.NonNull
    private String lastname;

    // Rélation (sings) avec la table Song
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sings",
            joinColumns = @JoinColumn(name = "singer_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;
}
