package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;




@Repository

public interface MovieRepository extends JpaRepository<Movie, Integer> {

   Optional<Movie> findAllMoviesByTitle(String title);


    @Query("select c from Movie c " +
            "where lower(c.title) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.relesaseDate) like lower(concat('%', :searchTerm, '%'))")
    List<Movie> title(@Param("title") String searchTerm);
}



