package com.example.application.data.service;

import com.example.application.data.Role;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.security.email.EmailSenderService;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;

@Service
public class UserService {

    private final UserRepository repository;
    private final EmailSenderService senderService;

    private AuthenticatedUser authenticatedUser;
    private User user;

    @Autowired
    public UserService(UserRepository repository, EmailSenderService senderService, AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.senderService = senderService;
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

    //Test for Routing
    public  User findById(long id) {
        if (id == 0 ) {
            return authenticatedUser.get().get();
        }
        else return repository.findById(id);
    }

    public  User findByUsername(String username) {
        if (username.isEmpty()) {
            return authenticatedUser.get().get();
        }
        else return repository.findByUsername(username);
    }
    public void registerUser(User user){
        repository.save(user);
    }

    public boolean insertWatchedList(User user, Movie movie){
        boolean movieDoesExists = false;
        for(Movie movie2: user.getWatchedMovies()) {
            if(movie.getMovieID() != movie2.getMovieID()){

            }else{
                movieDoesExists = true;
            }
        }
        if(movieDoesExists == false) {
            user.getWatchedMovies().add(movie);
            repository.save(user);
            return true;
        }
        return false;
    }

    public void sendAdminMail(String bug) {
        for (User user: findAllUsers(null)) {
            if (user.getRoles().contains(Role.ADMIN) && user.getEmail() != null) {
                senderService.sendEmail(user.getEmail(),"Bug Report", bug);
            }
        }
    }

    public String insertWatchList (User user, List<Movie> movieList){

        List<Movie> watchlist = user.getWatchList();
        List<Movie> watchedMovies = user.getWatchedMovies();
        for (Movie movieFromWatchlist  : watchlist){
            for(Movie newMovie : movieList) {
                if (newMovie.getMovieID() == movieFromWatchlist.getMovieID()) {
                    break;
                }
            }

        }
        for (Movie movieFromWatchedMovies : watchedMovies){
            for(Movie newMovie : movieList) {
                if (newMovie.getMovieID() == movieFromWatchedMovies.getMovieID()) {
                    break;
                }
            }
        }

        watchlist.addAll(movieList);

        user.setWatchList(new ArrayList<>());
        repository.save(user);
        user.setWatchList(watchlist);
        try {
            repository.save(user).setWatchList(watchlist);
            return "Movie saved in Watchlist";
        }catch(Exception e){
            e.getSuppressed();
        }
        return "This is fine";
    }


    public void deleteWatchlist(User user, List<Movie> movieSet) throws ConcurrentModificationException {
        List<Movie> watchlist = user.getWatchList();
        if (watchlist.isEmpty()) {
            return;
        }
        for (int i = 0, sizei = movieSet.size(); sizei > i; i++) {
            Movie movieToDelete = movieSet.get(i);
            for (int j = 0, sizej = watchlist.size(); sizej > j; j++) {
                if (movieToDelete.getMovieID() == watchlist.get(j).getMovieID()) {
                    watchlist.remove(j);
                    j--;
                    sizej--;
                }
            }
        }
        user.setWatchList(watchlist);
        repository.save(user);
    }
    public void addFriend(User user, User user2) {
        if(user.getFriends().contains(user2)) {
            return;
        } else {
            user.getFriends().add(user2);
            repository.save(user);
        }
    }

    public boolean isFriend(User user, User user2) {
        if (user.getFriends().contains(user2) && user2.getFriends().contains(user)) {

            return true;
        }
        else {
            return false;
        }
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
