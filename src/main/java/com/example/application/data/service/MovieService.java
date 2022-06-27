package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Review;
import com.example.application.data.entity.User;
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

    public Movie findByTitle(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return null;
        } else {
            if(movieRepository.title(stringFilter).size()>0){
                System.out.println(movieRepository.title(stringFilter).get(0).getTitle());
            }
            return movieRepository.title(stringFilter).get(0);
        }
    }



    public List<Movie> findAllMoviesByGenre(Genre genre) {
        if (genre.getGenre() == null || genre.getGenre().isEmpty()) {
            return movieRepository.findAll();
        } else {
            List<Genre> genreList = new ArrayList();
            genreList = genreRepository.findAllByGenre(genre.getGenre());

            if(genreList.size() > 0){
                int selectedGenre = genreList.get(0).getGenreID();
            }
            return movieRepository.findAllMoviesByGenreList(genre);
        }
        //return null;
    }

    public long countMovies() {
        return movieRepository.count();
    }

    public double averageRating(Movie movie) {
        Integer ratingCount = movie.getReviewList().size();
        Double sum = 0.0;
        Double average = 0.0;

        for(Review review:movie.getReviewList()) {
            sum = sum + review.getStarReviewOntToFive();
        }

        average = sum / ratingCount;


        return average;
    }

    public void deleteMovieReviews(Movie movie) {
        movie.getReviewList().clear();
        movieRepository.save(movie);
    }

    public void addUserToWatched(User user, Movie movie) {
        movie.usersWatched.add(user);
        movieRepository.save(movie);
    }


//    public List<Movie> findWatchedMovies(String stringFilter){
//       return movieRepository.watched(stringFilter);
//    }


}


