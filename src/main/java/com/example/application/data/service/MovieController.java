package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableWebSecurity
@RestController()
@RequestMapping("movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){ this.movieService = movieService; }
    @GetMapping(value = "get", produces = "application/json")
    public List<Movie> getMovie(){
        return movieService.getMovie();
    }
    @PostMapping(value = "put", produces = "application/json")
    public void addNewMovie(@RequestBody Movie movie){
        System.out.println("Test");
        movieService.addNewMovie(movie);
    }
}
