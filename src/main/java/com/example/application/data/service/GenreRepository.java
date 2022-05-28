package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{

    List<Genre>findAllByGenre(String genre);

}
