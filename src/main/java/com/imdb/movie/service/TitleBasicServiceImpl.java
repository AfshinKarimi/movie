package com.imdb.movie.service;

import com.imdb.movie.domain.TitleBasics;
import com.imdb.movie.repository.TitleBasicsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleBasicServiceImpl implements TitleBasicService {

    private final TitleBasicsRepository titleBasicsRepository;


    @Override
    public List<TitleBasics> getTitlesWithSameDirectorAndWriterAlive() {
        return titleBasicsRepository.findTitlesWithSameDirectorAndWriterAlive();
    }

    @Override
    public List<TitleBasics> getTitlesByActors(String actor1, String actor2) {
        if (actor1.trim().isEmpty() || actor2.trim().isEmpty()) {
            throw new IllegalArgumentException("Actor names cannot be blank");
        }
        return titleBasicsRepository.findTitlesWithTwoActors(actor1, actor2);
    }

    @Override
    public List<TitleBasics> getBestTitlesForGenre(String genre) {
        return titleBasicsRepository.findBestTitlesForGenreByYear(genre);
    }
}
