package com.imdb.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "title_episode")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleEpisode {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst; // Episode identifier

    @Column(name = "parentTconst", length = 255)
    private String parentTconst; // Parent TV series identifier

    @Column(name = "seasonNumber")
    private Integer seasonNumber; // Season number the episode belongs to

    @Column(name = "episodeNumber")
    private Integer episodeNumber; // Episode number in the season
}

