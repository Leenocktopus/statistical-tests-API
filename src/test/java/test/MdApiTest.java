package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import randombits.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MdApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUnprocessableEntityOnBadTestParameters() throws Exception {
        this.mockMvc.perform(post("/md_test/two")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"0\"}")
        )
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.message")
                        .value("Illegal sequence length ( <3): 1"));

        this.mockMvc.perform(post("/md_test/three")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"abcdef\"}")
        )
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.message")
                        .value("Sequence should contain only ones (1) and zeroes (0)."));
    }

    @Test
    public void shouldReturnResultWhenGoodParameters() throws Exception {
        this.mockMvc.perform(post("/md_test/one")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sequence\": \"110010010000111111010101000100010000101100010100010111000\"}"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"value\": 0.012888100881170567}"));
    }

}
