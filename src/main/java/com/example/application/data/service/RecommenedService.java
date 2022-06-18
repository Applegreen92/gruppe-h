package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RecommenedService {

    private final MovieService movieService;
    private final AuthenticatedUser authenticatedUser;
    private final GenreRepository genreRepository;
    private Genre mostviewedGenre;

    @Autowired
    public RecommenedService(MovieService movieService, AuthenticatedUser authenticatedUser, GenreRepository genreRepository) {
        this.movieService = movieService;
        this.authenticatedUser = authenticatedUser;
        this.genreRepository = genreRepository;
    }

    public List<Movie> getRecommendedMovies() {
        this.mostviewedGenre = genreCount();
        List<Movie> recommendedMovieList = new ArrayList<>();
        for (Movie movie:movieService.findAllMoviesByGenre(mostviewedGenre)) {
            if(this.authenticatedUser.get().get().getWatchedMovies().contains(movie)) {

            } else {
                recommendedMovieList.add(movie);
                if (recommendedMovieList.size() == 15) {
                    return recommendedMovieList;
                }
            }
        }
        return recommendedMovieList;
    }

    private Genre genreCount() {
        List<Movie> copyWatchedList = new ArrayList<>();
        List<Genre> genreList = genreRepository.findAll();
        int[] genreCounts =new int[genreList.size()];
        int mostViewedGenreInt = 0;
        int mostviewedGenreIndex = 0;
        copyWatchedList = this.authenticatedUser.get().get().getWatchedMovies();
        for (Movie movie: copyWatchedList) {
            for (Genre genre: movie.getGenreArrayList()) {
                for(int i= 0; i<genreList.size(); i++) {
                    if(genre == genreList.get(i)) {
                        genreCounts[i]++;
                    };
                }
        }
        }
        for(int i= 0; i<genreCounts.length; i++) {
            if (genreCounts[i] > mostViewedGenreInt) {
                mostViewedGenreInt = genreCounts[i];
                mostviewedGenreIndex = i;
            }
        }
        return genreList.get(mostviewedGenreIndex);
    }
}
