package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.vaadin.flow.router.Route;
import org.atmosphere.config.service.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping(value = "movie",method = {RequestMethod.GET,RequestMethod.POST})
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){ this.movieService = movieService; }
    @GetMapping(produces = "application/json")
    public List<Movie> getMovie(){
        return movieService.getMovie();
    }
    @PostMapping()
    public void addNewMovie(@RequestBody Movie movie){
        System.out.println("Test");
        movieService.addNewMovie(movie);
    }
}
