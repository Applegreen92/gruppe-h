package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Recommendation;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }
    public List<Recommendation> getAll(){
        return recommendationRepository.findAll();
    }
    public void input(Recommendation reco){
        recommendationRepository.save(reco);
    }


    public List<Recommendation> findByReceiverUserId(long userId){

        List<Recommendation> recoList = recommendationRepository.findAll();
        List<Recommendation> selectedReco = new ArrayList<>();
        for(Recommendation recommendation : recoList){
            if(recommendation.getToUserId() == userId){
                selectedReco.add(recommendation);
            }
        }
        return selectedReco;
    }

    public void deleteRecommendationR(int movieId, long userId){
        List<Recommendation> recommendations = findByReceiverUserId(userId);
        for(int i = 0, size = recommendations.size(); i < size; i++){
            if(recommendations.get(i).getMovieId() == movieId){
                recommendationRepository.deleteById(recommendations.get(i).getRecommendationId());

                return;
            }
        }
    }
    public void insert(Recommendation recommendation, long userId){
        List<Recommendation> recoList = findByReceiverUserId(userId);
        for(Recommendation reco : recoList){
            if(reco.getMovieId() == recommendation.getMovieId() && reco.getToUserId() == userId){
                Notification.show("Allready send this Movie to this User");
                return;
            }

        }

    }


}
