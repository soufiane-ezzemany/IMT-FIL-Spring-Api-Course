package imt.fil.cl.leja.songmanagerapi.song;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import imt.fil.cl.leja.songmanagerapi.singer.Singer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Song")
public class Song {
    @Id
    // Génération de la clé lors d'une insertion en base de données.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    @lombok.NonNull
    private Long songId;
    @NotNull
    @lombok.NonNull
    private String title;
    @NotNull
    @lombok.NonNull
    @Column(name = "release_year")
    private Integer releaseYear;
    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
    @NotNull
    @lombok.NonNull
    private Float rating;

    // Rélation (sings) avec la table Song
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties("songs")
    @ManyToMany(mappedBy = "songs", cascade = CascadeType.ALL)
    private Set<Singer> singers;

    public Song(SongDTO songDTO) {
        this.title = songDTO.getTitle();
        this.releaseYear = songDTO.getReleaseYear();
        this.rating = songDTO.getRating();
    }
}
