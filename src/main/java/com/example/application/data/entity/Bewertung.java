package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table
public class Bewertung {



    @Id
    @SequenceGenerator(
            name = "Bewertung_Sequenz",
            sequenceName = "Bewertung_Sequenz",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Bewertung_Sequenz"
    )
    private int Id;

}
