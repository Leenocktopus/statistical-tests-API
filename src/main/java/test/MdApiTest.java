package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import randombits.Application;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MdApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUnprocessableEntityOnBadTestParameters() throws Exception {

    }

    @Test
    public void shouldReturnResultWhenGoodParameters() throws Exception {

    }

}
