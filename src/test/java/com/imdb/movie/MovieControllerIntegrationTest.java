package com.imdb.movie;

import com.imdb.movie.domain.TitleBasics;
import com.imdb.movie.repository.TitleBasicsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TitleBasicsRepository titleBasicsRepository;

    @BeforeEach
    void setUp() {
        TitleBasics title1 = new TitleBasics();
        title1.setTconst("tt1234567");
        title1.setTitleType("movie");
        title1.setPrimaryTitle("Test Movie 1");
        title1.setOriginalTitle("Test Movie 1");
        title1.setIsAdult(false);
        title1.setStartYear("2020");
        title1.setEndYear(null);
        title1.setRuntimeMinutes(120);
        title1.setGenres("Action");

        TitleBasics title2 = new TitleBasics();
        title2.setTconst("tt2345678");
        title2.setTitleType("movie");
        title2.setPrimaryTitle("Test Movie 2");
        title2.setOriginalTitle("Test Movie 2");
        title2.setIsAdult(false);
        title2.setStartYear("2021");
        title2.setEndYear(null);
        title2.setRuntimeMinutes(110);
        title2.setGenres("Comedy");

        titleBasicsRepository.saveAll(Arrays.asList(title1, title2));
    }

    @AfterEach
    void tearDown() {
        titleBasicsRepository.deleteAll();
    }

    @Test
    void testGetTitlesWithSameDirectorAndWriterAlive() throws Exception {
        mockMvc.perform(get("/api/v1/movie/same-director-writer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetTitlesByActors_ValidActors() throws Exception {
        mockMvc.perform(get("/api/v1/movie/titles-by-actors")
                        .param("actor1", "Actor One")
                        .param("actor2", "Actor Two")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetTitlesByActors_EmptyActor_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/movie/titles-by-actors")
                        .param("actor1", "")
                        .param("actor2", "Actor Two")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors['getTitlesByActors.actor1']").value("Actor1 must not be null or empty"));
    }

    @Test
    void testGetBestTitlesByGenre_ValidGenre() throws Exception {
        mockMvc.perform(get("/api/v1/movie/best-titles-by-genre")
                        .param("genre", "Action")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetBestTitlesByGenre_EmptyGenre_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/movie/best-titles-by-genre")
                        .param("genre", "") // Empty genre
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors['getBestTitlesByGenre.genre']").value("genre must not be null or empty"));
    }


}