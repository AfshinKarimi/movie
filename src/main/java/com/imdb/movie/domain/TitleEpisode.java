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
    private String tconst;

    @Column(name = "parentTconst", length = 255)
    private String parentTconst;

    @Column(name = "seasonNumber")
    private Integer seasonNumber;

    @Column(name = "episodeNumber")
    private Integer episodeNumber;
}

