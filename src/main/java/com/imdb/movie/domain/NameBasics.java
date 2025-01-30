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
    private String nconst; // Unique identifier of the person

    @Column(name = "primaryName", length = 255)
    private String primaryName; // Name by which the person is most often credited

    @Column(name = "birthYear", length = 4)
    private String birthYear; // Birth year in YYYY format

    @Column(name = "deathYear", length = 4)
    private String deathYear; // Death year in YYYY format, or null if not applicable

    @Column(name = "primaryProfession", length = 500)
    private String primaryProfession; // Stored as a comma-separated string (e.g., "actor,producer,director")

    @Column(name = "knownForTitles", length = 1000)
    private String knownForTitles; // Stored as a comma-separated string (e.g., "tt0000001,tt0000002")
}


