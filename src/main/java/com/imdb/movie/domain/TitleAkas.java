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
@Table(name = "title_akas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleAkas {

    @Id
    @Column(name = "titleId", nullable = false)
    private String titleId;

    @Column(name = "ordering", nullable = false)
    private Integer ordering;

    @Column(name = "title")
    private String title;

    @Column(name = "region", length = 50)
    private String region;

    @Column(name = "language", length = 50)
    private String language;

    @Column(name = "types")
    private String types;

    @Column(name = "attributes")
    private String attributes;

    @Column(name = "isOriginalTitle")
    private Boolean isOriginalTitle;
}