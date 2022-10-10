package com.ada.challenge.moviebattle.integration;

import com.ada.challenge.moviebattle.domain.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String EXISTING_USERNAME = "alex.souza";
    private static final String EXISTING_PASSWORD = "123";

    @Test
    public void givenPlayerLogin_whenRequestIsOk_thenReturnSuccess() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/player/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                LoginRequest.builder()
                                        .username(EXISTING_USERNAME)
                                        .password(EXISTING_PASSWORD)
                                        .build())))
                .andExpect(status().isOk());
    }

    @Test
    public void givenPlayerLogin_whenRequestIsIncorrect_thenReturnUnauthorized() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/player/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                LoginRequest.builder()
                                        .username(EXISTING_USERNAME)
                                        .password("wrong")
                                        .build())))
                .andExpect(status().isUnauthorized());
    }
}
