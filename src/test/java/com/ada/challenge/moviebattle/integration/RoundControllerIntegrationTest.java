package com.ada.challenge.moviebattle.integration;

import com.ada.challenge.moviebattle.controller.dto.VoteDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
    private static final String EXISTING_ROUND_ID = "1f90a112-cbb4-4fda-ba7f-969b66f132f2";
    private static final String NON_EXISTING_ROUND_ID = "1f90a112-cbb4-4fda-ba7f-969b66f132f3";
    private static final String EXISTING_MOVIE_ID = "tt1537282";

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenCreateRound_whenGameExists_thenReturnSuccess() throws Exception {
        mockMvc.perform(post("/api/game/"+EXISTING_GAME_ID+"/round")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenCreateRound_whenGameDoesNotExist_thenReturnNotFound() throws Exception {
        mockMvc.perform(post("/api/game/"+NON_EXISTING_GAME_ID+"/round")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:script/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:script/game.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:script/round.sql", executionPhase = BEFORE_TEST_METHOD)})
    public void givenSelectMovie_whenRoundExists_thenReturnSuccess() throws Exception {
        mockMvc.perform(patch("/api/round/"+EXISTING_ROUND_ID+"/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                VoteDTO.builder()
                                        .movieId(EXISTING_MOVIE_ID)
                                        .build())))
                .andExpect(status().isNoContent());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:script/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:script/game.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:script/round.sql", executionPhase = BEFORE_TEST_METHOD)})
    public void givenSelectMovie_whenMovieIsNotInTheRound_thenReturnBadRequest() throws Exception {
        mockMvc.perform(patch("/api/round/"+EXISTING_ROUND_ID+"/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                VoteDTO.builder()
                                        .movieId(EXISTING_MOVIE_ID)
                                        .build())))
                .andExpect(status().isNoContent());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:script/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:script/game.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:script/round.sql", executionPhase = BEFORE_TEST_METHOD)})
    public void givenSelectMovie_whenRoundDoesNotExist_thenReturnNotFound() throws Exception {
        mockMvc.perform(patch("/api/round/"+NON_EXISTING_ROUND_ID+"/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                VoteDTO.builder()
                                        .movieId(EXISTING_MOVIE_ID)
                                        .build())))
                .andExpect(status().isNotFound());
    }

}
