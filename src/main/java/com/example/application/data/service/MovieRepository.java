package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);
    Optional<Movie> findAllByTitleAndReleaseDate(String title, int releaseDate);
}
