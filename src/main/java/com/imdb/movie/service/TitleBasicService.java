package com.imdb.movie.service;

import com.imdb.movie.domain.TitleBasics;

import java.util.List;

public interface TitleBasicService {

    List<TitleBasics> getTitlesWithSameDirectorAndWriterAlive();
    List<TitleBasics> getTitlesByActors(String actor1, String actor2);
    List<TitleBasics> getBestTitlesForGenre(String genre);
}
