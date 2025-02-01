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
    private String tconst;

    @Id
    @Column(name = "ordering", nullable = false)
    private Integer ordering;

    @Column(name = "nconst", length = 255)
    private String nconst;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "job", length = 255)
    private String job;

    @Column(name = "characters", length = 1000)
    private String characters;
}

