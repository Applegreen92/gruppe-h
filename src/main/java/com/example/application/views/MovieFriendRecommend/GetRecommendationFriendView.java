package com.example.application.views.MovieFriendRecommend;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Recommendation;
import com.example.application.data.service.MovieRepository;
import com.example.application.data.service.RecommendationService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Friend recommendations")
@Route(value = "friend-recommendation", layout = MainLayout.class)
@PermitAll
public class GetRecommendationFriendView extends Div {

    TextField filterText = new TextField();
    Grid<Movie> grid = new Grid<>(Movie.class);

    List<Movie> movieList = new ArrayList<>();
    List<Recommendation> recoList = new ArrayList<>();

    private final RecommendationService recommendationService;
    private final AuthenticatedUser authenticatedUser;
    private final MovieRepository movieRepository;
    private final UserService userService;

    public GetRecommendationFriendView(RecommendationService recommendationService, AuthenticatedUser authenticatedUser, MovieRepository movieRepository, UserService userService) {
        this.recommendationService = recommendationService;
        this.authenticatedUser = authenticatedUser;
        this.movieRepository = movieRepository;
        this.userService = userService;
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        updateList();

    }
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());


        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {

        grid.setSizeFull();
        grid.setColumns("title", "releaseDate");
        grid.addColumn(Movie::getLength).setHeader("Length");
        grid.addColumn(Movie::getGenreList).setHeader("Genre");


        grid.addColumn(new ComponentRenderer<>(Button::new, (button, Movie) -> {

            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e-> {
                    userService.insertWatchList(authenticatedUser.get().get(), Movie);
                    for(int i = 0, size = recoList.size() ; i < size ; i++){
                        if(recoList.get(i).getMovieId() == Movie.getMovieID()){
                            recommendationService.deleteRecommendationR(recoList.get(i).getMovieId(),authenticatedUser.get().get().getId());
                            updateList();

                            break;


                        }
                    }});

            button.setIcon(new Icon(VaadinIcon.PLUS));
        })).setHeader("Accept");

//        grid.addColumn(new ComponentRenderer<>(Button::new, (button, Movie) -> {
//            List<Movie> moviel = new ArrayList<>();
//            moviel.add(Movie);
//            button.addThemeVariants(ButtonVariant.LUMO_ICON,
//                    ButtonVariant.LUMO_ERROR,
//                    ButtonVariant.LUMO_TERTIARY);
//            button.addClickListener(e->
//
//            button.setIcon(new Icon(VaadinIcon.PLUS));
//        })).setHeader("Decline");
    }

    public void updateList(){
        recoList.clear();
        movieList.clear();
        this.recoList = recommendationService.findByReceiverUserId(authenticatedUser.get().get().getId());
        for (Recommendation recommendation : recoList){
            this.movieList.add(movieRepository.getById(recommendation.getMovieId()));
        }
        grid.setItems(this.movieList);


    }




}
