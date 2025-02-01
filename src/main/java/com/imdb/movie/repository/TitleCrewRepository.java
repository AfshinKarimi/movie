package com.imdb.movie.repository;

import com.imdb.movie.domain.TitleAkas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleCrewRepository extends JpaRepository<TitleAkas, String>{

}
