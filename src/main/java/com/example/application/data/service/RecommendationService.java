package com.example.application.data.service;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Recommendation;
import com.example.application.data.entity.User;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(MovieRepository movieRepository, UserRepository userRepository, RecommendationRepository recommendationRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.recommendationRepository = recommendationRepository;
    }
    public List<Recommendation> getAll(){
        return recommendationRepository.findAll();
    }



    public List<Recommendation> receiverRecommendations(long userId){

        List<Recommendation> recoList = recommendationRepository.findAll();
        List<Recommendation> selectedReco = new ArrayList<>();
        for(Recommendation recommendation : recoList){
            if(recommendation.getToUserId() == userId){
                selectedReco.add(recommendation);
            }
        }
        return selectedReco;
    }
    public List<Recommendation> senderRecommendations(long userId){

        List<Recommendation> recoList = recommendationRepository.findAll();
        List<Recommendation> selectedReco = new ArrayList<>();
        for(Recommendation recommendation : recoList){
            if(recommendation.getFromUserId() == userId){
                selectedReco.add(recommendation);
            }
        }
        return selectedReco;
    }

    public void deleteRecommendationR(int movieId, long userId){
        List<Recommendation> recommendations = receiverRecommendations(userId);
        for(int i = 0, size = recommendations.size(); i < size; i++){
            if(recommendations.get(i).getMovieId() == movieId){
                recommendationRepository.deleteById(recommendations.get(i).getRecommendationId());

                return;
            }
        }
    }
    public void input(Recommendation recommendation){
        List<Recommendation> recoList = receiverRecommendations(recommendation.getToUserId());
        for(Recommendation reco : recoList){
            if(reco.getMovieId() == recommendation.getMovieId() && reco.getToUserId() == recommendation.getToUserId()){
                Notification.show("Allready send this Movie to this User");
                return;
            }

        }
        Notification.show("Recommendation send");
        recommendationRepository.save(recommendation);

    }
    public void update(Recommendation recommendation){

        recommendationRepository.deleteById(recommendation.getRecommendationId());
        recommendationRepository.save(recommendation);


    }
    public User getUser(long userId){
        return userRepository.findById(userId);
    }

    public Movie getMovie(int movieId){
        List<Movie> movieList = new ArrayList<>();
        movieList.addAll(movieRepository.findAll());
        for ( Movie movie : movieList){
            if(movie.getMovieID() == movieId){
                return movie;
            }
        }
        return null;
    }



}
