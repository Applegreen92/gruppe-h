package com.example.application.data.entity;

import javax.persistence.*;

@Entity
@Table
public class Review {



    public Review( String userReview, int starReviewOntToFive, Long userID ) {
        this.starReviewOntToFive = starReviewOntToFive;
        this.userReview = userReview;
        this.userID = userID;

    }

    public Review() {}


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
    private int reviewID;

    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID() {
        this.userID = userID;
    }

    private int starReviewOntToFive;

    private String userReview;

    public void setStarReviewOntToFive(int starReviewOntToFive) {
        if(starReviewOntToFive < 1 || starReviewOntToFive > 5){
            System.out.println("Number has to be from 1 to 5");
        }else{
            this.starReviewOntToFive = starReviewOntToFive;
        }
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public int getReviewId() {
        return reviewID;
    }

    public int getStarReviewOntToFive() {
        return starReviewOntToFive;
    }

    public String getUserReview() {
        return userReview;
    }
}
