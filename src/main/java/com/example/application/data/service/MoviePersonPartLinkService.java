package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MoviePersonPartLinkService {
    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    @Autowired
    public MoviePersonPartLinkService(PersonRepository personRepository, MovieRepository movieRepository, GenreRepository genreRepository) {
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public void addNewMovie(ArrayList<Movie> MovieList) {
        System.out.println("Test");
        for(int x = 0; x < MovieList.size()-1; x++){
            Movie movie = MovieList.get(x);
            System.out.println(movie);
            movieRepository.save(movie);
//            for(int i = 0; i < movie.getGenreList().size();i++){
//                genreRepository.save(movie.getGenreList().get(i));
//                System.out.println(movie.getGenreList().get(i));
//            }

        }


//        System.out.println(movie);
//        movieRepository.save(movie);
//        if(movie.getMovieID() != 0) {
//            System.out.println("Test2");
//            Optional<Movie> movieByTitleAndReleaseDate = movieRepository.findAllByTitleAndReleaseDate(movie.getTitle(), movie.getReleaseDate());
//            if (movieByTitleAndReleaseDate.isEmpty()) {
//                System.out.println("Test3");
//                movieRepository.save(movie);
//            } else {
//                throw new IllegalStateException();
//            }
//            movie.getMovieID();
//        }
    }
}
