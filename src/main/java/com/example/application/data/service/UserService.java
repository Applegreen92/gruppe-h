package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        user.getWatchList().add(movie);
    }

    public void addFriend(User user, User user2) {
        user.getFriends().add(user2);
        repository.save(user);
    }

}
