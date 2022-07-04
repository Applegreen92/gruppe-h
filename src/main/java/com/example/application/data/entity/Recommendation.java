package com.example.application.data.entity;

import com.example.application.data.service.RecommendationService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private long toUserId;
    private long fromUserId;
    private int movieId;
    private String text;
    private boolean isAccepted;
    private boolean sendBack;



    public Recommendation(long fromUserId, long toUserId, int movieId, String text) {
        this.toUserId = toUserId;
        this.movieId = movieId;
        this.fromUserId = fromUserId;
        this.text = text;
    }

    public Recommendation() {

    }


    public boolean isSendBack() {
        return sendBack;
    }

    public void setSendBack(boolean sendBack) {
        this.sendBack = sendBack;
    }
    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }




}
