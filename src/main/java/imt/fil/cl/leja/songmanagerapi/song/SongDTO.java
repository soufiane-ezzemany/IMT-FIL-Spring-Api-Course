package imt.fil.cl.leja.songmanagerapi.song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SongDTO {

    private Long songId;

    private String title;

    private Integer releaseYear;

    private Float rating;
}
