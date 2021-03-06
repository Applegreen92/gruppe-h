package com.example.application.data.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Movie {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private int movieID;
    private String title;
    private String posterSrc;
    private int releaseDate;
    private int length;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "movieGenre",
            joinColumns = @JoinColumn(name = "genre_movieID",referencedColumnName = "movieID"),
            inverseJoinColumns = @JoinColumn(name = "movie_genreID",referencedColumnName = "genreID")
    )
    private List<Genre> genreList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(name = "movie_reviews",
    joinColumns = @JoinColumn( referencedColumnName = "movieID"),
    inverseJoinColumns = @JoinColumn(referencedColumnName = "reviewID"))
    List<Review> reviewList = new ArrayList<>();

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Transient
    double averageRating;

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Transient
    String personDirector;
    @Transient
    ArrayList<Genre> genreArrayList = new ArrayList<>();
    @Transient
    ArrayList<Person> personAuthorList = new ArrayList<>();
    @Transient
    ArrayList<Person> personCastList = new ArrayList<>();


    @OneToMany(mappedBy = "movie")
    private Set<MoviePersonPartLink> moviePersonPartLink;

    @OneToMany(mappedBy = "movieUserWatched", fetch = FetchType.LAZY)
    private Set<WatchedMovies> watchedMovies;

    @ManyToMany(mappedBy = "watchedMovies", cascade = {CascadeType.MERGE})
    public List<User> usersWatched = new ArrayList<>();

    @ManyToMany(mappedBy = "watchList", cascade = {CascadeType.MERGE})
    public List<User> usersWatch = new ArrayList<>();

    public Movie() {
    }

    public List<Genre> getGenreArrayList() {

        return genreArrayList;
    }

    public void setGenreArrayList(ArrayList<Genre> genreArrayList) {

        this.genreArrayList = genreArrayList;
    }

    public Movie(String title, String posterSrc, int releaseDate, int length, ArrayList tempGenreArray, String personDirector, ArrayList personAuthorList, ArrayList personCastList) {
        this.title = title;
        this.posterSrc = posterSrc;
        this.releaseDate = releaseDate;
        this.length = length;
        this.genreArrayList = tempGenreArray;
        this.personDirector = personDirector;
        this.personAuthorList = personAuthorList;
        this.personCastList = personCastList;
//        for(Genre genre: genreList){
//            this.genre+=genre.getGenre();
//        }
    }
    //todo erase
    public Movie(String title, String toString, int parseInt, int convertLength, ArrayList tempWriterArray, ArrayList tempCastArray) {
    }

    public String getGenres() {
        StringBuffer sb = new StringBuffer();
        for (Genre genre: genreArrayList) {
            sb.append(genre.getGenre());
            sb.append(", ");
        }
        String str = sb.toString();
        return str;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }
    public void clearGenreList() {
        this.genreList.clear();
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }
    public void setGenreList(Genre genre) {
        genreList.add(genre);
    }

    public String getPersonDirector() {
        return personDirector;
    }

    public void setPersonDirector(String personDirector) {
        this.personDirector = personDirector;
    }

    public ArrayList<Person> getPersonAuthorList() {
        return personAuthorList;
    }

    public void setPersonAuthorList(ArrayList<Person> personAuthorList) {
        this.personAuthorList = personAuthorList;
    }

    public ArrayList<Person> getPersonCastList() {
        return personCastList;
    }

    public void setPersonCastList(ArrayList<Person> personCastList) {
        this.personCastList = personCastList;
    }


    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterSrc() {
        return posterSrc;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public int getLength() {
        return length;
    }



    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterSrc(String posterSrc) {
        this.posterSrc = posterSrc;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", title='" + title + '\'' +
                ", posterSrc='" + posterSrc + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                ", personDirector='" + personDirector + '\'' +
                ", genreArrayList=" + genreArrayList +
                ", personAuthorList=" + personAuthorList +
                ", personCastList=" + personCastList +
                '}';
    }
}
