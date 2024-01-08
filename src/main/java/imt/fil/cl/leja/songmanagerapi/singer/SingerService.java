package imt.fil.cl.leja.songmanagerapi.singer;

import imt.fil.cl.leja.songmanagerapi.singer.projections.SingerInfoOnly;
import imt.fil.cl.leja.songmanagerapi.song.Song;
import imt.fil.cl.leja.songmanagerapi.song.SongDTO;
import imt.fil.cl.leja.songmanagerapi.song.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public void addSinger(SingerDTO singer) {
        // Créez une instance de Singer avec le prénom et le nom fournis
        Singer singerComplete = new Singer();
        singerComplete.setFirstname(singer.getFirstName());
        singerComplete.setLastname(singer.getLastName());

        // Ajoutez le chanteur à la base de données en utilisant le repository
        singerRepository.save(singerComplete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addSongsToSinger(Singer singer, Set<SongDTO> songs) {
        if (singer == null) {
            throw new RuntimeException("Singer not found");
        }
        for (SongDTO songDTO : songs) {
            Song song;
            if (songDTO.getSongId() == null) {
                song = new Song(songDTO);
            } else {
                song = getSongById(songDTO.getSongId());
            }
            singer.getSongs().add(song);
            songRepository.save(song);
        }
    }

    public Singer getSingerById(Long singerId) {
        return singerRepository.findById(singerId).orElse(null);
    }

    public Song getSongById(Long songId) {
        return songRepository.findById(songId).orElse(null);
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

