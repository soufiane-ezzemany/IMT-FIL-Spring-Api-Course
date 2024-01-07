package imt.fil.cl.leja.songmanagerapi.singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerService {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public void addSinger(SingerCreateDTO singer) {
        // Créez une instance de Singer avec le prénom et le nom fournis
        Singer singerComplete = new Singer();
        singerComplete.setFirstname(singer.getFirstName());
        singerComplete.setLastname(singer.getLastName());

        // Ajoutez le chanteur à la base de données en utilisant le repository
        singerRepository.save(singerComplete);
    }

    public Optional<Singer> getSingerAndBestSongs(Long singerId) {
        // Renvoie le chanteur avec la liste des meilleurs chansons
        return singerRepository.findByIdWithBestSongs(singerId, 4);
    }
}

