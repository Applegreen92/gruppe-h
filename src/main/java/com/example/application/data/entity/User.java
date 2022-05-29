package com.example.application.data.entity;

import com.example.application.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class User extends AbstractEntity {

    private String username;
    private String firstname;
    private String lastname;
    private String name;


    @ManyToMany
    @JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    private List<User> friends = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_watch_list", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> watchList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_watched_movies", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> watchedMovies = new ArrayList<>();




    private LocalDate geburtsdatum;

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonIgnore
    private String hashedPassword;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Lob
    private String profilePictureUrl;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
    public List<User> getFriends() {
        return friends;
    }
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }
    public void setWatchedMovies(List<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }
    public List<Movie> getWatchList() {
        return watchList;
    }
    public void setWatchList(List<Movie> watchList) {
        this.watchList = watchList;
    }
    public User(String username , String password1, String vorname, String nachname, LocalDate geburtsdatum ) {
        this.username = username;
        this.firstname = vorname;
        this.lastname = nachname;
        this.geburtsdatum = geburtsdatum;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {

        };
        this.hashedPassword = passwordEncoder.encode(password1);
        setRoles(Collections.singleton(Role.USER));
    }
    public User() {

    }

}
