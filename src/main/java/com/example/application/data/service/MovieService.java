package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
    public void addNewMovie(Movie movie) {

        System.out.println("Test");
        Optional<Movie> movieByTitleAndReleaseDate = movieRepository.findAllByTitleAndReleaseDate(movie.getTitle(), movie.getReleaseDate());
        if(movieByTitleAndReleaseDate.isEmpty()){
            movieRepository.save(movie);
        }else {
            throw new IllegalStateException();
        }

    }

}
