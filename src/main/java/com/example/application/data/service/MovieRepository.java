package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);
    Optional<Movie> findAllByTitleAndReleaseDate(String title, int releaseDate);
}
