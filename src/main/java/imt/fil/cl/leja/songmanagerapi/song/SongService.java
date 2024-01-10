package imt.fil.cl.leja.songmanagerapi.song;

import imt.fil.cl.leja.songmanagerapi.singer.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class SongService {

    SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
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
                song = getSongById(songDTO.getSongId()).orElse(null);
            }
            singer.getSongs().add(song);
            songRepository.save(song);
        }
    }

    public Optional<Song> getSongById(Long songId) {
        return songRepository.findById(songId);
    }
}
