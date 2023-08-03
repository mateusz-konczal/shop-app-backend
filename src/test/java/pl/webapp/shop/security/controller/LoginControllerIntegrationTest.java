package pl.webapp.shop.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.webapp.shop.MySQLTestContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("container")
class LoginControllerIntegrationTest {

    @Container
    private static final MySQLContainer<MySQLTestContainer> MYSQL_CONTAINER = MySQLTestContainer.getInstance();

    private static final String SECURED_API_URL = "/admin/secured";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_login_and_get_content() throws Exception {
        // WHEN & THEN
        MvcResult loginResult = mockMvc.perform(post("/login")
                        .content(getLoginCredentials())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String token = loginResult.getResponse().getContentAsString().substring(10, 175);
        mockMvc.perform(get(SECURED_API_URL).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("secured"));
    }

    @Test
    void should_not_return_content_when_not_logged_in() throws Exception {
        // WHEN & THEN
        mockMvc.perform(get(SECURED_API_URL))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    String getLoginCredentials() {
        return "{\"username\": \"admin\", \"password\": \"test\"}";
    }
}
