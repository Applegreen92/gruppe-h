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

        } else return repository.search(stringFilter);
    }

    //Test for Routing
    public User findById(long id) {
        if (id == 0) {
            return authenticatedUser.get().get();
        } else return repository.findById(id);
    }

    public User findByUsername(String username) {
        if (username.isEmpty()) {
            return authenticatedUser.get().get();
        } else return repository.findByUsername(username);
    }

    public void registerUser(User user) {
        repository.save(user);
    }

    public boolean insertWatchedList(User user, Movie movie) {
        boolean movieDoesExists = false;
        for (Movie movie2 : user.getWatchedMovies()) {
            if (movie.getMovieID() != movie2.getMovieID()) {

            } else {
                movieDoesExists = true;
            }
        }
        if (movieDoesExists == false) {
            user.getWatchedMovies().add(movie);
            repository.save(user);
            return true;
        }
        return false;
    }

    public void sendAdminMail(String bug) {
        for (User user : findAllUsers(null)) {
            if (user.getRoles().contains(Role.ADMIN) && user.getEmail() != null) {
                senderService.sendEmail(user.getEmail(), "Bug Report", bug);
            }
        }
    }

    public String insertWatchList(User user, List<Movie> movieList) {

        List<Movie> watchlist = user.getWatchList();
        List<Movie> watchedMovies = user.getWatchedMovies();


        for (int i = 0, iSize = movieList.size(); i < iSize; i++) {
            Movie newMovie = movieList.get(i);
            for (int j = 0, jSize = watchlist.size(); j < jSize; j++) {
                Movie movieWatchlist = watchlist.get(j);
                if (newMovie.getMovieID() == movieWatchlist.getMovieID()) {
                    movieList.remove(i);
                    i--;
                    iSize--;
                    break;
                }
            }
        }

        watchlist.addAll(movieList);

        user.setWatchList(watchlist);
        repository.save(user);
        return "Movie/s Saved on Watchlist";

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

    public boolean isFriendTest(User user5, User user6) {
        boolean found = false;
        for(User friend : user5.getFriends()) {
            if (friend.getUsername() == user6.getUsername()) {
                found = true;
                break;
            } else {
                continue;
            }
        }
            return found;
    }

    public void printFriends(User user) {
        for(User friend : user.getFriends()) {
            System.out.print(friend.getUsername() + " ");
        }
    }


    public boolean isFriend(User user0, User user1) {
        boolean userB = false;
        boolean user2B = false;
        for (User friend : user0.getFriends()) {
            if (friend.getUsername().equals(user1.getUsername())) {
                userB = true;
                break;
            } else { continue; }
            }
        for (User friend : user1.getFriends()) {
            if (friend.getUsername().equals(user0.getUsername())) {
                user2B = true;
                break;
            } else {
                continue;
            }
        }
        if (userB && user2B == true) {
            return true;
        } else {
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
    public void changePrivacyRecommendedMovies(User user2, Integer integer) {
        if(user2.getRecommendedMoviesPrivacy()==0) {
            user2.setRecommendedMoviesPrivacy(integer);
            repository.save(user2);

        }
        else if(user2.getRecommendedMoviesPrivacy()==1 && integer != 1) {
            user2.setRecommendedMoviesPrivacy(integer);
            repository.save(user2);
        }
        else Notification.show("No changes done");
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

    public void updateRecommendedPrivacyString(User user) {
        if(user.getRecommendedMoviesPrivacy() == 0) {
            user.setRecommendedP("MY WATCHED MOVIES");
            repository.save(user);
        }
        else if (user.getRecommendedMoviesPrivacy() == 1) {
            user.setRecommendedP("FRIENDS WATCHED MOVIES");
            repository.save(user);
        }
    }

    public void updatePrivacyString(User user) {
        if (user.getWatchListPrivacy() == 0) {
            user.setWatchListP("DEFAULT");
            repository.save(user);
        } else if (user.getWatchListPrivacy() == 1) {
            user.setWatchListP("PUBLIC");
            repository.save(user);
        } else if (user.getWatchListPrivacy() == 2) {
            user.setWatchListP("ONLY FRIENDS");
            repository.save(user);
        } else if (user.getWatchListPrivacy() == 3) {
            user.setWatchListP("PRIVATE");
            repository.save(user);
        }


        if (user.getWatchedMoviesPrivacy() == 0) {
            user.setWatchedMoviesP("DEFAULT");
            repository.save(user);
        } else if (user.getWatchedMoviesPrivacy() == 1) {
            user.setWatchedMoviesP("PUBLIC");
            repository.save(user);
        } else if (user.getWatchedMoviesPrivacy() == 2) {
            user.setWatchedMoviesP("ONLY FRIENDS");
            repository.save(user);
        } else if (user.getWatchedMoviesPrivacy() == 3) {
            user.setWatchedMoviesP("PRIVATE");
            repository.save(user);
        }


        if (user.getFriendListPrivacy() == 0) {
            user.setFriendListP("DEFAULT");
            repository.save(user);
        } else if (user.getFriendListPrivacy() == 1) {
            user.setFriendListP("PUBLIC");
            repository.save(user);
        } else if (user.getFriendListPrivacy() == 2) {
            user.setFriendListP("ONLY FRIENDS");
            repository.save(user);
        } else if (user.getFriendListPrivacy() == 3) {
            user.setFriendListP("PRIVATE");
            repository.save(user);
        }
    }


    public List<User> getAllUser(){
        return repository.findAll();
    }
}
