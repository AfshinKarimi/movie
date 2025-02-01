package com.imdb.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "name_basics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameBasics {

    @Id
    @Column(name = "nconst", nullable = false)
    private String nconst;

    @Column(name = "primaryName", length = 255)
    private String primaryName;

    @Column(name = "birthYear", length = 4)
    private String birthYear;

    @Column(name = "deathYear", length = 4)
    private String deathYear;

    @Column(name = "primaryProfession", length = 500)
    private String primaryProfession;

    @Column(name = "knownForTitles", length = 1000)
    private String knownForTitles;
}


