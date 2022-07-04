package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendedService {

    private final MovieService movieService;
    private final AuthenticatedUser authenticatedUser;
    private final GenreRepository genreRepository;
    private Genre mostviewedGenre;

    @Autowired
    public RecommendedService(MovieService movieService, AuthenticatedUser authenticatedUser, GenreRepository genreRepository) {
        this.movieService = movieService;
        this.authenticatedUser = authenticatedUser;
        this.genreRepository = genreRepository;
    }

    public List<Movie> getRecommendedMovies() {
        this.mostviewedGenre = genreCount();
        List<Movie> preSelectedMovieList = new ArrayList<>();
        List<Movie> recommendedMovieList = new ArrayList<>();
        preSelectedMovieList = movieService.findAllMoviesByGenre(mostviewedGenre);
        for(Movie movie: preSelectedMovieList){
            movie.setAverageRating(movieService.averageRating(movie));
        }

        Comparator<Movie> comparator = Comparator.comparing(movie -> movie.getAverageRating(), Collections.reverseOrder());
        comparator = comparator.thenComparing(Comparator.comparing(movie -> movie.getTitle()));
        preSelectedMovieList.sort(comparator);



        for (Movie movie:preSelectedMovieList) {
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
        if(authenticatedUser.get().get().getRecommendedMoviesPrivacy() == 0){
          copyWatchedList = this.authenticatedUser.get().get().getWatchedMovies();
        }else {
            for(int i = 0; i < this.authenticatedUser.get().get().getFriends().size(); i++){
                copyWatchedList.addAll(this.authenticatedUser.get().get().getFriends().get(i).getWatchedMovies());
            }
        }
        if(copyWatchedList.size()>=10) {
            for (int i = 0; i < copyWatchedList.size(); i++) {
                if (i == 0) {
                    i = copyWatchedList.size() - 10;
                }
                for (Genre genre : copyWatchedList.get(i).getGenreList()) {
                    for (int x = 0; i < genreList.size(); x++) {
                        if (genre.getGenre().equals(genreList.get(x).getGenre())) {
                            genreCounts[x]++;
                        }
                        ;
                    }
                }
            }
        }else{
            for (Movie movie: copyWatchedList) {
                for (Genre genre: movie.getGenreList()) {
                    for(int i= 0; i<genreList.size(); i++) {
                        if(genre.getGenre().equals(genreList.get(i).getGenre())) {
                            genreCounts[i]++;
                        };
                    }
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

//    private Genre genreCountByFriends() {
//        List<Movie> copyWatchedList = new ArrayList<>();
//        List<Genre> genreList = genreRepository.findAll();
//        int[] genreCounts =new int[genreList.size()];
//        int mostViewedGenreInt = 0;
//        int mostviewedGenreIndex = 0;
//
//        for(int i = 0; i < this.authenticatedUser.get().get().getFriends().size(); i++){
//            copyWatchedList.addAll(this.authenticatedUser.get().get().getFriends().get(i).getWatchedMovies());
//        }
//
//        for (Movie movie: copyWatchedList) {
//            for (Genre genre: movie.getGenreArrayList()) {
//                for(int i= 0; i<genreList.size(); i++) {
//                    if(genre == genreList.get(i)) {
//                        genreCounts[i]++;
//                    };
//                }
//            }
//        }
//        for(int i= 0; i<genreCounts.length; i++) {
//            if (genreCounts[i] > mostViewedGenreInt) {
//                mostViewedGenreInt = genreCounts[i];
//                mostviewedGenreIndex = i;
//            }
//        }
//        return genreList.get(mostviewedGenreIndex);
//    }
}
