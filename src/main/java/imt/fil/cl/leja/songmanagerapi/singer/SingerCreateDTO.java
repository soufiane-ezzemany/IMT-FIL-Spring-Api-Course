package imt.fil.cl.leja.songmanagerapi.singer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SingerCreateDTO {

    private String firstName;

    private String lastName;
}