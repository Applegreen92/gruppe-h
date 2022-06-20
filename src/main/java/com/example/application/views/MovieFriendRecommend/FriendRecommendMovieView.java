package com.example.application.views.MovieFriendRecommend;


import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserRepository;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;

import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
@PageTitle("Recommend a movie")
@Route(value = "recommendToFriend",layout = MainLayout.class)
@PermitAll
public class FriendRecommendMovieView extends Div implements HasUrlParameter<Integer> {


    private final AuthenticatedUser authenticatedUser;
    private final UserRepository userRepository;
    private final MovieService movieService;

    private Movie movie;

















    @Autowired
    public FriendRecommendMovieView(AuthenticatedUser authenticatedUser, MovieService movieService, UserService userService, UserRepository userRepository) {
        this.authenticatedUser = authenticatedUser;
        this.movieService = movieService;
        this.userRepository = userRepository;
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {

    }
}
