package com.example.application.views.MovieFriendRecommend;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Friend recommendations")
@Route(value = "friend-recommendation", layout = MainLayout.class)
@PermitAll
public class GetRecommendationFriendView extends Div {


}
