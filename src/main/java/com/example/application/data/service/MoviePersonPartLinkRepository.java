package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.MoviePersonPartLink;
import com.example.application.data.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviePersonPartLinkRepository extends JpaRepository<MoviePersonPartLink, Integer> {

    List<MoviePersonPartLink> findAllPersonsByMovieAndPart(Movie movieid, Part partid);
}
