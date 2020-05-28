package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import randombits.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc

public class ErrorsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHttpNotFoundIfNoHandler() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message")
                        .value("No handler found on server for this URL."));
    }

    @Test
    public void shouldReturnHttpBadRequestIfNoSuchTest() throws Exception {
        this.mockMvc.perform(post("/nist_test/ffrequency"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message")
                        .value("Url parameter should be one of: frequency, block_frequency, " +
                                "binary_matrix, non_overlapping_template, overlapping_template, spectral, " +
                                "linear_complexity, longest_run_of_ones, maurers, cumulative_sums, " +
                                "random_excursions, excursion_variant, runs, serial, entropy."));

        this.mockMvc.perform(post("/md_test/abc123xyz"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message")
                        .value("Url parameter should be one of: " +
                                "one, two, three, four, five, six, seven, eight."));
    }


    @Test
    public void shouldReturnMethodNotAllowed() throws Exception {
        this.mockMvc.perform(get("/nist_test/cumulative_sums"))
                .andExpect(status().is(405))
                .andExpect(jsonPath("$.message")
                        .value("GET method is not supported by this URL."));
        this.mockMvc.perform(get("/md_test/eight"))
                .andExpect(status().is(405))
                .andExpect(jsonPath("$.message")
                        .value("GET method is not supported by this URL."));
    }

    @Test
    public void shouldReturnBadRequestOnIllegalOrEmptyBody() throws Exception {
        this.mockMvc.perform(post("/nist_test"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message")
                        .value("Request body is not readable."));
    }

    @Test
    public void shouldReturnBadRequestOnMissingRequestParams() throws Exception {
        this.mockMvc.perform(post("/md_test/"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message")
                        .value("Missing request parameters."));
    }
}
