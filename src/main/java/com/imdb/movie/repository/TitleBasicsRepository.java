package com.imdb.movie.repository;

import com.imdb.movie.domain.TitleBasics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleBasicsRepository  extends JpaRepository<TitleBasics, String> {

    @Query("SELECT t FROM TitleBasics t JOIN TitleCrew c ON t.tconst = c.tconst " +
            "JOIN NameBasics n ON c.directors = n.nconst AND c.writers = n.nconst " +
            "WHERE n.deathYear IS NULL")
    List<TitleBasics> findTitlesWithSameDirectorAndWriterAlive();

    @Query("SELECT t FROM TitleBasics t JOIN TitlePrincipals p1 ON t.tconst = p1.tconst " +
            "JOIN TitlePrincipals p2 ON t.tconst = p2.tconst " +
            "WHERE p1.nconst = :actor1 AND p2.nconst = :actor2")
    List<TitleBasics> findTitlesWithTwoActors(@Param("actor1") String actor1, @Param("actor2") String actor2);


    @Query("SELECT t FROM TitleBasics t JOIN TitleRatings r ON t.tconst = r.tconst " +
            "WHERE t.genres LIKE CONCAT('%', :genre, '%') " +
            "GROUP BY t.tconst, t.startYear, t.endYear, t.genres, t.isAdult, t.originalTitle, " +
            "t.primaryTitle, t.runtimeMinutes, t.titleType " +
            "ORDER BY r.numVotes DESC, r.averageRating DESC")
    List<TitleBasics> findBestTitlesForGenreByYear(@Param("genre") String genre);



}
