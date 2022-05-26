package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/insert")
public class MoviePersonPartLinkController {
    private final MoviePersonPartLinkService moviePersonPartLinkService;

    @Autowired
    public MoviePersonPartLinkController(MoviePersonPartLinkService moviePersonPartLinkService) {
        this.moviePersonPartLinkService = moviePersonPartLinkService;
    }
    @GetMapping
    public String getMovie(){
        return "Hello";
//        return List.of(
//            new Movie(
//                    1,
//                    "test",
//                    "Testposter",
//                    2000,
//                    45
//            )
//        );
    }


//    @PostMapping
//    public void addNewMovie(@RequestBody Ar movie){
//    System.out.println("Test");
//        moviePersonPartLinkService.addNewMovie(movie);
//    }
}
