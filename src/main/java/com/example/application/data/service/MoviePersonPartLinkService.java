package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.MoviePersonPartLink;
import com.example.application.data.entity.Part;
import com.example.application.data.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MoviePersonPartLinkService {
    public final MoviePersonPartLinkRepository moviePersonPartLinkRepository;
    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final PartRepository partRepository;
    @Autowired
    public MoviePersonPartLinkService(MoviePersonPartLinkRepository moviePersonPartLinkRepository, PersonRepository personRepository, MovieRepository movieRepository, GenreRepository genreRepository, PartRepository partRepository) {
        this.moviePersonPartLinkRepository = moviePersonPartLinkRepository;
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.partRepository = partRepository;
    }

    public void addNewMovie(ArrayList<Movie> MovieList) {
        if(partRepository.findAllByPartName("director").isEmpty()){
            Part partDirector = new Part("director");
            partRepository.save(partDirector);
        }
        if(partRepository.findAllByPartName("Author").isEmpty()){
            Part partAuthor = new Part("Author");
            partRepository.save(partAuthor);
        }
        if(partRepository.findAllByPartName("Cast").isEmpty()){
            Part partCast = new Part("Cast");
            partRepository.save(partCast);
        }
        // director = 1
        // Author = 2
        // Cast = 3
        for(int x = 0; x <= MovieList.size()-1; x++) {
            Movie movie = MovieList.get(x);
            for (int i = 0; i < movie.getGenreArrayList().size(); i++) {
                if (genreRepository.findAllByGenre(movie.getGenreArrayList().get(i).getGenre()).isEmpty()) {
                    genreRepository.save(movie.getGenreArrayList().get(i));
                    movie.setGenreList(movie.getGenreArrayList().get(i));

                } else {
                    movie.setGenreList(genreRepository.findAllByGenre(movie.getGenreArrayList().get(i).getGenre()).get(0));
                }
            }
            movieRepository.save(movie);
            Person person = null;
            if (movie.getPersonDirector() != "") {
                String name = movie.getPersonDirector();
                String nameArr[] = name.split(" ", 2);
                if(nameArr.length > 1) {
                    String firstname = nameArr[0];
                    String lastname = nameArr[1];

                    person = new Person(firstname, lastname);
                    if (personRepository.findAllByFirstnameAndLastname(firstname, lastname) == null) {
                        personRepository.save(person);
                        MoviePersonPartLink moviePersonPartLink = new MoviePersonPartLink(movie, person, partRepository.findAllByPartName("director").get(0));
                        moviePersonPartLinkRepository.save(moviePersonPartLink);
                    } else {
                        MoviePersonPartLink moviePersonPartLink = new MoviePersonPartLink(movie, personRepository.findAllByFirstnameAndLastname(firstname, lastname), partRepository.findAllByPartName("director").get(0));
                        moviePersonPartLinkRepository.save(moviePersonPartLink);
                    }
                }
            }
            if(movie.getPersonAuthorList().size()!=0){
                for(int i = 0; i < movie.getPersonAuthorList().size(); i++) {
                    String name = String.valueOf(movie.getPersonAuthorList().get(i));
                    String nameArr[] = name.split(" ", 2);
                    if (nameArr.length > 1) {
                        String firstname = nameArr[0];
                        String lastname = nameArr[1];
                        person = new Person(firstname, lastname);
                        if (personRepository.findAllByFirstnameAndLastname(firstname, lastname) == null) {
                            personRepository.save(person);
                            MoviePersonPartLink moviePersonPartLink = new MoviePersonPartLink(movie, person, partRepository.findAllByPartName("Author").get(0));
                            moviePersonPartLinkRepository.save(moviePersonPartLink);
                        } else {
                            MoviePersonPartLink moviePersonPartLink = new MoviePersonPartLink(movie, personRepository.findAllByFirstnameAndLastname(firstname, lastname), partRepository.findAllByPartName("Author").get(0));
                            moviePersonPartLinkRepository.save(moviePersonPartLink);
                        }
                    }
                }
            }
            if(movie.getPersonCastList().size()!=0){
                for(int i = 0; i < movie.getPersonCastList().size(); i++) {
                    String name = String.valueOf(movie.getPersonCastList().get(i));
                    String nameArr[] = name.split(" ", 2);
                    if (nameArr.length > 1) {
                        String firstname = nameArr[0];
                        String lastname = nameArr[1];
                        person = new Person(firstname, lastname);
                        if (personRepository.findAllByFirstnameAndLastname(firstname, lastname) == null) {
                            personRepository.save(person);
                            MoviePersonPartLink moviePersonPartLink = new MoviePersonPartLink(movie, person, partRepository.findAllByPartName("Cast").get(0));
                            moviePersonPartLinkRepository.save(moviePersonPartLink);
                        } else {
                            MoviePersonPartLink moviePersonPartLink = new MoviePersonPartLink(movie, personRepository.findAllByFirstnameAndLastname(firstname, lastname), partRepository.findAllByPartName("Cast").get(0));
                            moviePersonPartLinkRepository.save(moviePersonPartLink);
                        }
                    }
                }
            }
        }
    }
}
