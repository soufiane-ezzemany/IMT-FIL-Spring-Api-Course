package imt.fil.cl.leja.songmanagerapi.singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingerService {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public void addSinger(String firstname, String lastname) {
        // Créez une instance de Singer avec le prénom et le nom fournis
        Singer singer = new Singer();
        singer.setFirstname(firstname);
        singer.setLastname(lastname);

        // Ajoutez le chanteur à la base de données en utilisant le repository
        singerRepository.save(singer);
    }
}
