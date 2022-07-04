package com.example.application.data.service;

import com.example.application.data.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface WatchedMoviesRepository extends JpaRepository<WatchedMovies, Integer> {

    List<WatchedMovies> findAllByUserWatchedID(User user);
}
