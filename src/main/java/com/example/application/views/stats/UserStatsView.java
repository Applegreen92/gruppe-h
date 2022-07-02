package com.example.application.views.stats;

import com.example.application.data.entity.User;
import com.example.application.data.service.*;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
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
import java.util.List;
import java.util.Map;

@PageTitle("User Stats")
@Route(value ="userStats" , layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class UserStatsView extends VerticalLayout implements HasUrlParameter<String> {
    private AuthenticatedUser authenticatedUser;

    private User user;

    private Double averageRating;
    private Integer ratingCount;
    private Integer watchedCount;


    private TextField totalWatchtime = new TextField("Total Watchtime");
    private TextField favActor = new TextField("Favourite Actor");
    private TextField favMovie = new TextField("Favourite Movie");
    private TextField favGenre = new TextField("Favourite Genre");

    private Button resetStats = new Button("Reset Stats", new Icon(VaadinIcon.REFRESH));

    private MovieService movieService;
    private MovieRepository movieRepository;
    private MoviePersonPartLinkRepository moviePersonPartLinkRepository;
    private PartRepository partRepository;

    private UserService usersevice;


    public UserStatsView(AuthenticatedUser authenticatedUser, MovieService movieService, MovieRepository movieRepository, MoviePersonPartLinkRepository moviePersonPartLinkRepository, PartRepository partRepository, UserService usersevice){

        this.authenticatedUser = authenticatedUser;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.moviePersonPartLinkRepository = moviePersonPartLinkRepository;
        this.partRepository = partRepository;
        this.usersevice = usersevice;
        this.user = authenticatedUser.get().get();
        createTitle();
    }

    private Component createTitle() {
        return new H3("Stats for: " + user.getUsername());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(totalWatchtime, favActor, favMovie);
        return formLayout;
    }

    private void fillTextFields(){


        //todo: watchedmovie Liste funktioniert noch gar nicht aus der Movie Sicht, nur aus der User Sicht.
        //todo: heißt also, es die usersWatched Liste wird noch nicht befüllt. Nur so kann man aber die Views für einen Film zählen.

    }
    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        /*resetStats.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        //todo: RESET BUTTON hat noch keine Funktion. Soll aber später die Tables die angsprochen werden clearen.
        resetStats.addClickListener(e-> {
            movieService.deleteMovieReviews(displayedMovie);
            UI.getCurrent().getPage().reload();
        });
        */buttonLayout.add(resetStats);
        return buttonLayout;
    }




    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
        String name = parameter.substring(9);
        System.out.println(name);
        user = usersevice.findByUsername(name);


    }






}
