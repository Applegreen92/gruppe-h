package com.example.application.data.entity;

import com.example.application.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "application_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String name;


    @ManyToMany (cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    private List<User> friends = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "user_watch_list", joinColumns = @JoinColumn(name = "watchUserId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> watchList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_watched_movies", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> watchedMovies = new ArrayList<>();




    private String email;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public User(String username , String password1, String vorname, String nachname, LocalDate geburtsdatum, String email ) {
        this.username = username;
        this.firstname = vorname;
        this.lastname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.email = email;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {

        };
        this.hashedPassword = passwordEncoder.encode(password1);
        setRoles(Collections.singleton(Role.USER));
    }
    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", friends=" + friends +
                ", watchList=" + watchList +
                ", watchedMovies=" + watchedMovies +
                ", email='" + email + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", roles=" + roles +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                '}';
    }
}
