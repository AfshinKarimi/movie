package com.imdb.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "title_crew")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleCrew {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst;

    @Column(name = "directors", length = 1000)
    private String directors; // Stored as a comma-separated string (e.g., "nm0001,nm0002")

    @Column(name = "writers", length = 1000)
    private String writers; // Stored as a comma-separated string (e.g., "nm0003,nm0004")
}