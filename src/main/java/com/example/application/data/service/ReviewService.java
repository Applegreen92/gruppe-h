package com.example.application.data.service;

import com.example.application.data.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private final ReviewRepository reviewRepository;


    public void instertReview(Review review){
        reviewRepository.save(review);
    }
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
    public void delete(int reviewId){
        reviewRepository.deleteById(reviewId);
    }



}
