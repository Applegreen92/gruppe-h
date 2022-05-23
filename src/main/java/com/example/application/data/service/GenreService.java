package com.example.application.data.service;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenre(){
        return genreRepository.findAll();
    }

    public Genre update(Genre entity) {
        return genreRepository.save(entity);
    }

    public void delete(int genreID) {
        genreRepository.deleteById(genreID);
    }

    public void addNewGenre(Genre genre) {genreRepository.save(genre);}
}
