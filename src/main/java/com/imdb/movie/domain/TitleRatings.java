package com.imdb.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "title_ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleRatings {

    @Id
    @Column(name = "tconst", nullable = false)
    private String tconst; // Unique identifier of the title

    @Column(name = "averageRating", nullable = false)
    private Double averageRating; // Weighted average rating

    @Column(name = "numVotes", nullable = false)
    private Integer numVotes; // Number of votes received
}

