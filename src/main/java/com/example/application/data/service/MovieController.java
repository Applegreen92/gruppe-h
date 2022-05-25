package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){ this.movieService = movieService; }
    @GetMapping("getmovie")
    public List<Movie> getMovie(){
        return movieService.getMovie();
    }
    @PostMapping(path = "postmovie")
    public void addNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
    }
}
