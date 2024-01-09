package imt.fil.cl.leja.songmanagerapi.singer;

import imt.fil.cl.leja.songmanagerapi.song.Song;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingerServiceTest {

    @Mock
    private SingerRepository singerRepository;

    @InjectMocks
    private SingerService singerService;

    @Test
    public void testGetSingerAndBestSongs() {
        // Arrange
        Long singerId = 1L;
        int minRating = 4;
        Singer mockSinger = new Singer();
        mockSinger.setId(singerId);
        mockSinger.setFirstname("Test");
        mockSinger.setLastname("Singer");
        Song mockSong1 = new Song(2L, "Test song 1", 2024, 5f);
        Song mockSong2 = new Song(3L, "Test song 2", 2023, 4f);
        mockSinger.setSongs(Set.of(mockSong1, mockSong2));

        when(singerRepository.findByIdWithBestSongs(singerId, minRating)).thenReturn(Optional.of(mockSinger));

        // Act
        Optional<Singer> result = singerService.getSingerAndBestSongs(singerId);

        // Assert
        assertEquals(mockSinger, result.orElse(null));
        verify(singerRepository).findByIdWithBestSongs(singerId, minRating);
    }

    @Test
    void testAddSinger() {
        // Création d'un objet SingerDTO pour le test
        SingerDTO singerDTO = new SingerDTO("John", "Doe");

        // Appel de la méthode addSinger
        singerService.addSinger(singerDTO);

        // Vérification que la méthode save du repository a été appelée avec les bonnes valeurs
        verify(singerRepository).save(any(Singer.class));
    }

    @Test
    void testGetAll(){

        // Appel de la méthode addSinger
        singerService.getAllSingers();

        // Vérification que la méthode save du repository a été appelée avec les bonnes valeurs
        verify(singerRepository).findAllBy();

    }
    @Test
    void getSingerById(){
        Singer singer = new Singer(1L, "John", "Doe");
        singerService.getSingerById(singer.getId());
        verify(singerRepository).findById(singer.getId());
    }
}