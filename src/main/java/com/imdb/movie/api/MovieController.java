package com.imdb.movie.api;

import com.imdb.movie.domain.TitleBasics;
import com.imdb.movie.service.TitleBasicService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
@Validated
public class MovieController {

    private final TitleBasicService titleBasicService;

    @GetMapping("/same-director-writer")
    public ResponseEntity<List<TitleBasics>> getTitlesWithSameDirectorAndWriterAlive() {
        return ResponseEntity.ok(titleBasicService.getTitlesWithSameDirectorAndWriterAlive());
    }

    @GetMapping("/titles-by-actors")
    public ResponseEntity<List<TitleBasics>> getTitlesByActors(
            @RequestParam @NotBlank(message = "Actor1 must not be null or empty") String actor1,
            @RequestParam @NotBlank(message = "Actor2 must not be null or empty") String actor2) {
        return ResponseEntity.ok(titleBasicService.getTitlesByActors(actor1, actor2));
    }


    @GetMapping("/best-titles-by-genre")
    public ResponseEntity<List<TitleBasics>> getBestTitlesByGenre(
            @RequestParam @NotBlank(message = "genre must not be null or empty") String genre) {
        return ResponseEntity.ok(titleBasicService.getBestTitlesForGenre(genre));
    }
}
