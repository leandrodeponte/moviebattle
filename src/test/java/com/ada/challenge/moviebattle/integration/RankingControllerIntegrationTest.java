package com.ada.challenge.moviebattle.integration;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:script/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:script/game.sql", executionPhase = BEFORE_TEST_METHOD)})
public class RankingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String RANDOM_GAME_ID = UUID.randomUUID().toString();
    private static final String EXISTING_GAME_ID = "1f90a112-cbb4-4fda-ba7f-969b66f132f2";
    private static final String EXISTING_PLAYER_ID = "b5a3e5a0-4820-11ed-b878-0242ac120002";

    @Test
    public void givenGetRanking_whenExists_thenReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/ranking", EXISTING_GAME_ID)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}