package com.imdb.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "title_basics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleBasics {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @Column(name = "titleType", length = 50)
    private String titleType;

    @Column(name = "primaryTitle")
    private String primaryTitle;

    @Column(name = "originalTitle" )
    private String originalTitle;

    @Column(name = "isAdult")
    private Boolean isAdult;

    @Column(name = "startYear", length = 4)
    private String startYear;

    @Column(name = "endYear", length = 4)
    private String endYear;

    @Column(name = "runtimeMinutes")
    private Integer runtimeMinutes;

    @Column(name = "genre")
    private String genres; // Stored as a comma-separated string 11407938
}

