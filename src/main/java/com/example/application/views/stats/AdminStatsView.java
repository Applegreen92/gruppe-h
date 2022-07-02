package com.example.application.views.stats;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.*;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;



@PageTitle("Admin Stats")
@Route(value ="adminStats" , layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)

public class AdminStatsView extends VerticalLayout implements HasUrlParameter<String> {

    private AuthenticatedUser authenticatedUser;

    private Movie displayedMovie;
    private Double averageRating;
    private Integer ratingCount;
    private Integer watchedCount;


    private TextField averageT = new TextField("Average rating");
    private TextField ratingCountT = new TextField("Number of ratings");
    private TextField watchedCountT = new TextField("Number of views");

    private Button resetStats = new Button("Reset Stats", new Icon(VaadinIcon.REFRESH));


    private final UserService userService;
    private final UserRepository userRepository;
    private MovieService movieService;
    private MovieRepository movieRepository;
    private MoviePersonPartLinkRepository moviePersonPartLinkRepository;
    private PartRepository partRepository;


    public AdminStatsView(AuthenticatedUser authenticatedUser, UserService userService, UserRepository userRepository, MovieService movieService, MovieRepository movieRepository, MoviePersonPartLinkRepository moviePersonPartLinkRepository, PartRepository partRepository){

        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.userRepository = userRepository;

        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.moviePersonPartLinkRepository = moviePersonPartLinkRepository;
        this.partRepository = partRepository;




    }

    private Component createTitle() {
        return new H3("Stats for: " + displayedMovie.getTitle());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(averageT, ratingCountT, watchedCountT);
        return formLayout;
    }

    private void fillTextFields(){
        ratingCount = displayedMovie.getReviewList().size();
        averageRating = movieService.averageRating(displayedMovie);
        watchedCount = getWatchedCount();

        if(ratingCount == 0) {
            this.ratingCountT.setValue("No ratings yet");
            this.averageT.setValue("No ratings yet");
        }
        else {
            this.ratingCountT.setValue(String.valueOf(ratingCount));
            this.averageT.setValue(String.valueOf(averageRating));
        }
        if(watchedCount==0) {
            this.watchedCountT.setValue("No views yet");
        }
        else {
            this.watchedCountT.setValue(String.valueOf(watchedCount));
        }
    }

    private Integer getWatchedCount() {
        int count = 0;
        for (User user: userService.findAllUsers(null)) {
            for(Movie movie: user.getWatchedMovies()) {
                if (movie.getMovieID() == displayedMovie.getMovieID()) {
                    count++;
                }
            }
        }
        System.out.println("Anzahl Views: " + count);
        return count;

    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        resetStats.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        resetStats.addClickListener(e-> {
            movieService.deleteMovieReviews(displayedMovie);
            userService.deleteMovieFromAllUserWatchedLists(displayedMovie);
            UI.getCurrent().getPage().reload();
        });
        buttonLayout.add(resetStats);
        return buttonLayout;
    }






    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String title) {
        if(title== null || title.isEmpty()){
            this.ratingCountT.setValue("FEHLER");
            this.averageT.setValue("FEHLER");
            this.watchedCountT.setValue("FEHLER");

        }
        else {
            this.displayedMovie = movieRepository.getById(Integer.valueOf(title));

            add(createTitle());
            add(createFormLayout());
            add(createButtonLayout());
            fillTextFields();

        }
    }
}
