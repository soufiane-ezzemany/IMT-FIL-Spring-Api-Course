package imt.fil.cl.leja.songmanagerapi.song;

import imt.fil.cl.leja.songmanagerapi.singer.Singer;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Song")
public class Song {
    @Id
    // Génération de la clé lors d'une insertion en base de données.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;
    private String title;
    private Integer year;
    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private Float rating;

    // Rélation (sings) avec la table Song
    @ManyToMany(mappedBy = "songs")
    private List<Singer> singers;
}