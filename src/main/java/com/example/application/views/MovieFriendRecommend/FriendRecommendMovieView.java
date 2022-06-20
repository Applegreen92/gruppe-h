package com.example.application.views.MovieFriendRecommend;


import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserRepository;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

import java.awt.*;
import java.util.List;

@PageTitle("Recommend a movie")
@Route(value = "recommend",layout = MainLayout.class)
@PermitAll
public class FriendRecommendMovieView extends Div implements HasUrlParameter<Integer> {


    private final AuthenticatedUser authenticatedUser;
    private final UserRepository userRepository;
    private final MovieService movieService;
    private Movie movie;

    VerticalLayout verticalLayout = new VerticalLayout();
    TextArea textArea = new TextArea("Message");
    ComboBox<User> userBox = new ComboBox<>();

    public void createLayout(){


        textArea.setWidth("800px");
        textArea.setHeight("200px");

        userBox.setLabel("User you want to send the recommendation to");
        userBox.setPlaceholder("Choose user");
        userBox.setItems(userRepository.findAll());
        userBox.setItemLabelGenerator(User::getUsername);



        verticalLayout.add(textArea);
        verticalLayout.add(userBox);
        verticalLayout.add(buttons());

        add(verticalLayout);

    }
    public Component buttons(){
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button buttonSend = new Button("Send");
        Button buttonBack = new Button("Back");






        horizontalLayout.add(buttonSend,buttonBack);
        return horizontalLayout;
    }

















    @Autowired
    public FriendRecommendMovieView(AuthenticatedUser authenticatedUser, MovieService movieService, UserService userService, UserRepository userRepository) {
        this.authenticatedUser = authenticatedUser;
        this.movieService = movieService;
        this.userRepository = userRepository;
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {

        if(parameter == null){
            UI.getCurrent().navigate(MainLayout.class);
            Notification.show("Something went wrong !");
        }else{
            List<Movie> movieList = movieService.getMovie();

            for(Movie currentMovie : movieList){
                if(currentMovie.getMovieID() == parameter){
                    this.movie = currentMovie;
                    break;
                }
            }
            createLayout();
        }
    }
}
