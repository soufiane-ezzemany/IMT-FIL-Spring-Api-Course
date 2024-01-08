package imt.fil.cl.leja.songmanagerapi.singer;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(SingerController.class)
@ExtendWith(MockitoExtension.class)
class SingerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SingerService singerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAddSinger() throws Exception {
        // Création d'un objet SingerCreateDTO factice pour le test
        SingerCreateDTO singerCreateDTO = new SingerCreateDTO("Leo", "Le GOFF");

        // Simuler l'ajout du chanteur dans le service
        doNothing().when(singerService).addSinger(any(SingerCreateDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/singers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(singerCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Chanteur ajouté avec succès"));
    }

    @Test
    void testAddSingerException() throws Exception {
        // Création d'un objet SingerCreateDTO factice pour le test
        SingerCreateDTO singerCreateDTO = new SingerCreateDTO("Leo", "Le GOFF");

        doThrow(new RuntimeException("Error")).when(singerService).addSinger(any(SingerCreateDTO.class));

        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/singers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(singerCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Erreur lors de l'ajout du chanteur"));
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



}