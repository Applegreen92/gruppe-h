package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.MovieWatchedList;
import com.example.application.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieWatchedListService {
    @Autowired
    public final MovieWatchedListRepository movieWatchedListRepository;

    public MovieWatchedListService(MovieWatchedListRepository movieWatchedListRepository) {
        this.movieWatchedListRepository = movieWatchedListRepository;
    }

    public void insertWatchedList(User user, Movie movie){
        MovieWatchedList watchedList = new MovieWatchedList(movie,user);
        movieWatchedListRepository.save(watchedList);

    }
}
