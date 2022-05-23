package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovie(){
        return movieRepository.findAll();
    }

    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    public void delete(int movieID) {
        movieRepository.deleteById(movieID);
    }

    public void addNewMovie(Movie movie) {movieRepository.save(movie);}
}
