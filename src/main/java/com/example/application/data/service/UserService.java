package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.watchlist.Watchlist;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public String insertWatchList (User user, Movie movie){
        List<Movie> watchlist = user.getWatchList();
        List<Movie> watchedMovies = user.getWatchedMovies();
        for (Movie movieFromWatchlist  : watchlist){
            if(movie.getMovieID() == movieFromWatchlist.getMovieID()){
                return "Movie is already on watchlist";
            }

        }
        for (Movie movieFromWatchedMovies : watchedMovies){
            if(movie.getMovieID() == movieFromWatchedMovies.getMovieID()){
                return "Movie is already on Films you watched";
            }
        }


        user.getWatchList().add(movie);
        repository.save(user);
        return "Movie saved in Watchlist";
    }

    public void deleteWatchlist (User user, Movie movie) throws ConcurrentModificationException {
        List<Movie> watchlist = user.getWatchList();
        if(watchlist.isEmpty()){
            return;
        }
        for(int i = 0, size = watchlist.size()  ; size>i; i++){
            Movie movieToDelete = watchlist.get(i);
            if(movieToDelete.getMovieID() == movie.getMovieID()){
                watchlist.remove(movieToDelete);
            }
        }
        user.setWatchList(watchlist);
        repository.save(user);
    }
    public void addFriend(User user, User user2) {
        user.getFriends().add(user2);
        repository.save(user);
    }
    public void changePrivacyFriends(User user2, Integer integer) {
        if(user2.getFriendListPrivacy()==0) {
            user2.setFriendListPrivacy(integer);
            repository.save(user2);

        }
        else if(user2.getFriendListPrivacy()==1 && integer != 1) {
            user2.setFriendListPrivacy(integer);
            repository.save(user2);

        }
        else if (user2.getFriendListPrivacy()==2 && integer != 2){
            user2.setFriendListPrivacy(integer);
            repository.save(user2);

        }
        else if(user2.getFriendListPrivacy()==3 && integer != 3) {
            user2.setFriendListPrivacy(integer);
            repository.save(user2);

        }
    }
    public void changePrivacyWatchList(User user3, Integer integer) {
        if(user3.getWatchListPrivacy()==0) {
            user3.setWatchListPrivacy(integer);
            repository.save(user3);
        }
        else if(user3.getWatchListPrivacy()==1 && integer != 1) {
            user3.setWatchListPrivacy(integer);
            repository.save(user3);
        }
        else if (user3.getWatchListPrivacy()==2 && integer != 2){
            user3.setWatchListPrivacy(integer);
            repository.save(user3);
        }
        else if(user3.getWatchListPrivacy()==3 && integer != 3) {
            user3.setWatchListPrivacy(integer);
            repository.save(user3);
        }
        else Notification.show("No changes done");
    }
    public void changePrivacyWatched(User user4, Integer integer) {
        if(user4.getWatchedMoviesPrivacy()==0) {
            user4.setWatchedMoviesPrivacy(integer);
            repository.save(user4);
        }
        else if(user4.getWatchedMoviesPrivacy() ==1 && integer !=1) {
            user4.setWatchedMoviesPrivacy(integer);
            repository.save(user4);
        }
        else if (user4.getWatchedMoviesPrivacy()==2 && integer != 2){
            user4.setWatchedMoviesPrivacy(integer);
            repository.save(user4);
        }
        else if(user4.getWatchedMoviesPrivacy()==3 && integer != 3) {
            user4.setWatchedMoviesPrivacy(integer);
            repository.save(user4);
        }
        else Notification.show("No changes done");
    }

}
