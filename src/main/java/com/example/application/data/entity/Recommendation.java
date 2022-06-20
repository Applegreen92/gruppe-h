package com.example.application.data.entity;

import javax.persistence.*;

@Entity

public class Recommendation {
    @Id
    @SequenceGenerator(
            name = "Recommendation_Sequenz",
            sequenceName = "Recommendation_Sequenz",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Recommendation_Sequenz"
    )
    private int recommendationId;
    private int userId;
    private int movieId;




    public Recommendation(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
    public Recommendation() {

    }


    public int getUser() {
        return userId;
    }

    public int getMovie() {
        return movieId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }




}
