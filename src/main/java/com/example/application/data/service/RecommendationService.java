package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Recommendation;
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


    public List<Recommendation> findByToUserId(long userId){

        List<Recommendation> recoList = recommendationRepository.findAll();
        List<Recommendation> selectedReco = new ArrayList<>();
        for(Recommendation recommendation : recoList){
            if(recommendation.getToUserId() == userId){
                selectedReco.add(recommendation);
            }
        }
        return selectedReco;
    }
    public List<Recommendation> findByFromUserId(long userId){
        List<Recommendation> recoList = recommendationRepository.findAll();
        List<Recommendation> selectedReco = new ArrayList<>();
        for(Recommendation recommendation : recoList){
            if(recommendation.getFromUserId() == userId){
                selectedReco.add(recommendation);
            }
        }
        return selectedReco;
    }
    public void deleteByMovieId(int movieId){
        List<Recommendation> recommendations = recommendationRepository.findAll();
        for(int i = 0, size = recommendations.size(); i < size; i++){
            if(recommendations.get(i).getMovieId() == movieId){
                recommendations.remove(i);

                return;
            }
        }
    }


}
