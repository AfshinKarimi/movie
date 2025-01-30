package com.imdb.movie.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitlePrincipalsId implements Serializable {
    private String tconst;
    private Integer ordering;
}
