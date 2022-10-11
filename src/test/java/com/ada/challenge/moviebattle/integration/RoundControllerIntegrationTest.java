package com.ada.challenge.moviebattle.integration;

import com.ada.challenge.moviebattle.domain.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:script/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:script/game.sql", executionPhase = BEFORE_TEST_METHOD)})
public class RoundControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String EXISTING_GAME_ID = "1f90a112-cbb4-4fda-ba7f-969b66f132f2";

    private static final String NON_EXISTING_GAME_ID = "1f90a112-cbb4-4fda-ba7f-969b66f132f3";

    @Test
    public void givenPlayerLogin_whenRequestIsOk_thenReturnSuccess() throws Exception {
        mockMvc.perform(post("/api/game/"+EXISTING_GAME_ID+"/round")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenPlayerLogin_whenRequestIsIncorrect_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/api/game/"+NON_EXISTING_GAME_ID+"/round")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
