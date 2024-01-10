package imt.fil.cl.leja.songmanagerapi.song;

import com.fasterxml.jackson.databind.ObjectMapper;
import imt.fil.cl.leja.songmanagerapi.singer.Singer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.doReturn;

@WebMvcTest(SongController.class)
@ExtendWith(MockitoExtension.class)
class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getSongById() throws Exception {
        // Création d'un objet Song factice pour le test
        Long songId = 1L;
        Song song = new Song(songId, "Test song 1", 2024, 5f);
        Singer s1 = new Singer(1L, "travis", "scott");
        Singer s2 = new Singer(1L, "kayne", "west");
        song.setSingers(Set.of(s1, s2));

        doReturn(Optional.of(song)).when(songService).getSongById(songId);

        // Exécuter la requête et vérifier les résultats
        mockMvc.perform(MockMvcRequestBuilders.get("/api/songs/"+songId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(song)));
    }
}