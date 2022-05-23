package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
