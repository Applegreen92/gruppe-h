package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService  {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;



    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
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

        if(movie == null) {
        System.err.println("Movie object is empty");
        return;
        }

        System.out.println("Test");
        Optional<Movie> movieByTitle = movieRepository.findAllMoviesByTitle(movie.getTitle());
        if(movieByTitle.isEmpty()){
            movieRepository.save(movie);
        }else {
            throw new IllegalStateException();
        }

    }

    public List<Movie> findAllMoviesByTitle(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return movieRepository.findAll();
        } else {
            if(movieRepository.title(stringFilter).size()>0){
                System.out.println(movieRepository.title(stringFilter).get(0).getTitle());
            }
            return movieRepository.title(stringFilter);
        }
    }

    public List<Movie> findAllMoviesByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return movieRepository.findAll();
        } else {
//            List<Genre> genreList = new ArrayList();
//            genreList = genreRepository.findAllByGenre(genre);
//
//            if(genreList.size() > 0){
//                int selectedGenre = genreList.get(0).getGenreID();
//            }
//            return movieRepository.findAllByGenre(genre);
        }
        return null;
    }

    public long countMovies() {
        return movieRepository.count();
    }


    /*public List<Movie> findWatchedMovies(String stringFilter){


       return movieRepository.watched(stringFilter);
    }*/


}


