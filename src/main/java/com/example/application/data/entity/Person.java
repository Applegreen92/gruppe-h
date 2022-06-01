package com.example.application.data.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private int personID;
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "person")
    private Set<MoviePersonPartLink> moviePersonPartLink;

    public Person() {
    }

    public Person(int personID, String firstname, String lastname, int partID) {
        this.personID = personID;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
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
}
