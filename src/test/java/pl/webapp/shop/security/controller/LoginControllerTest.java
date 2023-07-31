package pl.webapp.shop.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    private static final String SECURED_API_URL = "/admin/secured";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldLoginAndGetContent() throws Exception {
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
    void shouldNotReturnContentWhenNotLoggedIn() throws Exception {
        // WHEN & THEN
        mockMvc.perform(get(SECURED_API_URL))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    String getLoginCredentials() {
        return "{\"username\": \"admin\", \"password\": \"test\"}";
    }
}
