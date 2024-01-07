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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

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


}