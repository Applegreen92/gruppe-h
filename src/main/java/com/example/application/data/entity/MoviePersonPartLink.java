package com.example.application.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class MoviePersonPartLink {
    @Id
    @SequenceGenerator(
            name = "moviePersonPartLink_sequence",
            sequenceName = "moviePersonPartLink_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "moviePersonPartLink_sequence"
    )
    private int moviePersonPartLinkID;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "partID")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "personID")
    private Person person;

    public MoviePersonPartLink() {
    }

    public MoviePersonPartLink(Movie movie, Person person, Part part) {
        this.movie = movie;
        this.person = person;
        this.part = part;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
