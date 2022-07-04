package com.example.application.data.service;

import com.example.application.data.entity.WatchedMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchedMoviesService {

    private final WatchedMoviesRepository watchedMoviesRepository;
    @Autowired
    public WatchedMoviesService(WatchedMoviesRepository watchedMoviesRepository){

        this.watchedMoviesRepository = watchedMoviesRepository;
    }

    public void insertWatched(WatchedMovies watchedMovies){

        watchedMoviesRepository.save(watchedMovies);
    }
}
