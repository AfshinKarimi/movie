package com.imdb.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "title_principals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TitlePrincipalsId.class)
public class TitlePrincipals {

    @Id
    @Column(name = "tconst", nullable = false, length = 255)
    private String tconst; // Unique identifier of the title

    @Id
    @Column(name = "ordering", nullable = false)
    private Integer ordering; // Unique row identifier for the title

    @Column(name = "nconst", length = 255)
    private String nconst; // Unique identifier of the person

    @Column(name = "category", length = 100)
    private String category; // Job category (e.g., actor, director)

    @Column(name = "job", length = 255)
    private String job; // Specific job title (or null if not applicable)

    @Column(name = "characters", length = 1000)
    private String characters; // Name of the character played (or null if not applicable)
}

