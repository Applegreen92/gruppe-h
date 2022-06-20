package com.example.application.views.MovieFriendRecommend;

import com.example.application.data.entity.Movie;
import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendRecommendMovieView extends Div  {

    private final AuthenticatedUser authenticatedUser;


















    @Autowired
    public FriendRecommendMovieView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }




}
