package imt.fil.cl.leja.songmanagerapi.singer;

import com.fasterxml.jackson.databind.ObjectMapper;
import imt.fil.cl.leja.songmanagerapi.song.Song;
import imt.fil.cl.leja.songmanagerapi.song.SongDTO;
import imt.fil.cl.leja.songmanagerapi.song.SongService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(SingerController.class)
@ExtendWith(MockitoExtension.class)
class SingerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SingerService singerService;

    @MockBean
    private SongService songService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAddSinger() throws Exception {
        // Création d'un objet SingerDTO factice pour le test
        SingerDTO singerDTO = new SingerDTO("Leo", "Le GOFF");

        // Simuler l'ajout du chanteur dans le service
        doNothing().when(singerService).addSinger(any(SingerDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/singers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(singerDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Chanteur ajouté avec succès"));
    }

    @Test
    void testAddSingerException() throws Exception {
        // Création d'un objet SingerDTO factice pour le test
        SingerDTO singerDTO = new SingerDTO("Leo", "Le GOFF");

        doThrow(new RuntimeException("Error")).when(singerService).addSinger(any(SingerDTO.class));

        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/singers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(singerDTO)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Erreur lors de l'ajout du chanteur"));
    }

    @Test
    void testgetSingerWithBestSongs() throws Exception {
        // Création d'un objet Singer factice pour le test
        Float minRating = 4f;
        Long singerId = 1L;
        Singer mockSinger = new Singer(singerId, "travis", "scott");
        Song mockSong1 = new Song(2L, "Test song 1", 2024, 5f);
        Song mockSong2 = new Song(3L, "Test song 2", 2023, 4f);
        mockSinger.setSongs(Set.of(mockSong1, mockSong2));

        doReturn(Optional.of(mockSinger)).when(singerService).getSingerAndBestSongs(singerId, minRating);

        // Exécuter la requête et vérifier les résultats
        mockMvc.perform(MockMvcRequestBuilders.get("/api/singers/"+singerId+"/bestsongs?minRating="+minRating))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockSinger)));
    }

    @Test
    void testgetSingerWithBestSongsNotFound() throws Exception {
        Float minRating = 4f;
        Long singerId = 1L;
        doReturn(Optional.empty()).when(singerService).getSingerAndBestSongs(singerId, minRating);

        // Exécuter la requête et vérifier les résultats
        mockMvc.perform(MockMvcRequestBuilders.get("/api/singers/"+singerId+"/bestsongs?minRating="+minRating))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAllSinger() throws Exception{
        // Créer une instance de Singer pour simuler les données de retour du service
        Singer singer = new Singer(1L, "anas", "alaoui");
        List<Singer> singers = List.of(singer);
        // Configurer le comportement du service mock
        doReturn(Optional.of(singers)).when(singerService).getAllSingers();

        // Exécuter la requête et vérifier les résultats
        mockMvc.perform(MockMvcRequestBuilders.get("/api/singers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(singers)));
    }

    @Test
    void testAddSongsToSinger() throws Exception{
        // Créer une instance de Singer pour simuler les données de retour du service
        Singer singer = new Singer(1L, "leo", "joly");

        // Créer une liste d'instances de SongDTO pour simuler les données de envoyer au service
        SongDTO songDTO = new SongDTO(1L, "test", 2024 , 5f);
        SongDTO songDTO2 = new SongDTO(2L, "test2", 2023 , 4f);
        Set<SongDTO> songs = Set.of(songDTO, songDTO2);

        // Configurer le comportement du service mock
        doNothing().when(songService).addSongsToSinger(singer, songs);

        // Exécuter la requête et vérifier les résultats
        mockMvc.perform(MockMvcRequestBuilders.post("/api/singers/{singerId}/add-songs", singer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songs)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}