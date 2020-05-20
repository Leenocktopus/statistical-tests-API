package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import randombits.Application;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class NistApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUnprocessableEntityOnBadTestParameters() throws Exception {
        this.mockMvc.perform(post("/nist_test/frequency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"1\"}")
        )
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.message")
                        .value("Illegal sequence length ( <3): 1"));

        this.mockMvc.perform(post("/nist_test/block_frequency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"1010101001\",\"blockSize\": \"100\"}")
        )
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.message")
                .value("Illegal block size ( > sequence || < 2): 100"));
        Random r = new Random();
        r.nextInt(1000);
        ;

        this.mockMvc.perform(post("/nist_test/excursion_variant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"101010103101\"}")
        )
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.message")
                        .value("Sequence should contain only ones (1) and zeroes (0)."));;
    }

    @Test
    public void shouldReturnResultWhenGoodParameters() throws Exception {
        this.mockMvc.perform(post("/nist_test/frequency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"101010101010101011111000001110101001010" +
                        "101010101010101010010101110100011111111\"}")
        )
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"pValue\": 0.3650302779165502, \"sequenceRandom\": true }"));
    }


}
