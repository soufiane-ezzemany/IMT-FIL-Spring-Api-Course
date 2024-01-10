package imt.fil.cl.leja.songmanagerapi.song;

import imt.fil.cl.leja.songmanagerapi.singer.Singer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {


    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void testAddSongsToSinger() {
        Singer singer = new Singer(1L, "John", "Doe");
        SongDTO songDTO = new SongDTO(null, "Test song", 2021, 5f);
        SongDTO songDTO2 = new SongDTO(2L, "Test song 2", 2021, 5f);
        singer.setSongs(new HashSet<>());
        Set<SongDTO> songs = new HashSet<>();
        songs.add(songDTO);
        songs.add(songDTO2);

        songService.addSongsToSinger(singer, songs);

        for (SongDTO song : songs) {
            verify(songRepository).save(any(Song.class));
        }
    }

    @Test
    void getSongById(){
        Song song = new Song(1L, "Test song", 2021, 5f);
        songService.getSongById(song.getSongId());
        verify(songRepository).findById(song.getSongId());
    }
}
