package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    private AuthenticatedUser authenticatedUser;

    @Autowired
    public UserService(UserRepository repository, AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.authenticatedUser = authenticatedUser;
    }

    public Optional<User> get(UUID id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    public List<User> findAllUsers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();

        }
        else return repository.search(stringFilter);
    }
    public void registerUser(User user){
        repository.save(user);
    }

    public void insertWatchedList(User user, Movie movie){
        user.getWatchedMovies().add(movie);
        repository.save(user);
    }

    public void insertWatchList (User user, Movie movie){
        List<Movie> watchlist = user.getWatchList();
        List<Movie> watchedMovies = user.getWatchedMovies();
        for (Movie movieFromWatchlist  : watchlist){
            if(movie.getMovieID() == movieFromWatchlist.getMovieID()){
                Notification.show("Movie is already on watchlist");
                return;
            }

        }
        for (Movie movieFromWatchedMovies : watchedMovies){
            if(movie.getMovieID() == movieFromWatchedMovies.getMovieID()){
                Notification.show("Movie is already on Films you watched");
                return;
            }
        }


        user.getWatchList().add(movie);
        repository.save(user);

    }

    public void deleteWatchlist (User user, Movie movie){
        List<Movie> watchlist = user.getWatchList();

        for(Movie movieToDelete : watchlist){
            if(movieToDelete.getMovieID() == movie.getMovieID()){
                System.out.println(movie.toString());
            }
        }
        repository.save(user);
        Notification.show("Movie/s successfully deleted.");
    }
    public void addFriend(User user, User user2) {
        user.getFriends().add(user2);
        repository.save(user);
    }

}
