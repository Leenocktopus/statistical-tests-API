package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import randombits.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc

public class ErrorsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHttpNotFoundIfNoHandler() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().is(404));
    }

    @Test
    public void shouldReturnHttpBadRequestIfNoSuchTest() throws Exception {
        this.mockMvc.perform(post("/nist_test/ffrequency")).andExpect(status().is(400));
    }


    @Test
    public void shouldReturnMethodNotAllowed() throws Exception {
        this.mockMvc.perform(get("/nist_test/cumulative_sums")).andExpect(status().is(405));
    }
}
