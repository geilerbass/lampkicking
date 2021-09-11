package geilerbass.lampkicking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LampkickingApplicationTests {

    @Autowired
    private MockMvc webTestClient;

    @Test
    void hooverPostRequestReturnsExpectedResult() throws Exception {
        String requestJson = "{\"roomSize\":[5,5],\"coords\":[1,2]," +
                "\"patches\":[[1,0],[2,2],[2,3]]," +
                "\"instructions\":\"NNESEESWNWW\"}";

        this.webTestClient.perform(
                post("/hoover")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"coords\" : [1, 3],\n" +
                        "  \"patches\" : 1\n" +
                        "}"));
    }

}
