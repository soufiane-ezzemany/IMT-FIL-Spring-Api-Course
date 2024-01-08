package imt.fil.cl.leja.songmanagerapi.singer;

import imt.fil.cl.leja.songmanagerapi.song.Song;
import imt.fil.cl.leja.songmanagerapi.song.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class SingerService {

    private final SingerRepository singerRepository;

    private final SongRepository songRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository, SongRepository songRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
    }

    public void addSinger(SingerCreateDTO singer) {
        // Créez une instance de Singer avec le prénom et le nom fournis
        Singer singerComplete = new Singer();
        singerComplete.setFirstname(singer.getFirstName());
        singerComplete.setLastname(singer.getLastName());

        // Ajoutez le chanteur à la base de données en utilisant le repository
        singerRepository.save(singerComplete);
    }

    @Transactional
    public void addSongsToSinger(Singer singer, Set<Song> songs) {
        for (Song song : songs) {
            song.getSingers().add(singer);
            singer.getSongs().add(song);
        }
        singerRepository.save(singer);
    }

    public Singer getSingerById(Long singerId) {
        return singerRepository.findById(singerId).orElse(null);
    }

    public Set<Song> getSongsByIds(Set<Long> songIds) {
        return songRepository.findAllById(songIds).orElse(null);
    }


    public Optional<Singer> getSingerAndBestSongs(Long singerId) {
        // Renvoie le chanteur avec la liste des meilleurs chansons
        return singerRepository.findByIdWithBestSongs(singerId, 4);
    }
    
    public Optional<List<SingerInfoOnly>> getAllSingers(){
        // Renvoie la liste des chanteurs
        return singerRepository.findAllBy();
    }
}

