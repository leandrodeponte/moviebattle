package com.ada.challenge.moviebattle.integration;

import com.ada.challenge.moviebattle.config.domain.GameStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String RANDOM_GAME_ID = UUID.randomUUID().toString();
    private static final String EXISTING_GAME_ID = "1f90a112-cbb4-4fda-ba7f-969b66f132f2";

    @Test
    public void givenSearchGameURI_whenDoesNotExist_thenReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/game/{gameId}", RANDOM_GAME_ID)
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SqlGroup({@Sql(value = "classpath:script/game.sql", executionPhase = BEFORE_TEST_METHOD)})
    public void givenSearchGameURI_whenExists_thenReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/game/{gameId}", EXISTING_GAME_ID)
                        .contentType("application/json"))
                .andExpect(jsonPath("$.id").value(EXISTING_GAME_ID))
                .andExpect(jsonPath("$.status").value(GameStatus.STARTED.toString()))
                .andExpect(status().isOk());
    }

}
