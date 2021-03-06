package com.example.application.views.movielist;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.*;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.example.application.views.MovieFriendRecommend.SendRecommendMovieView;
import com.example.application.views.MovieFriendRecommend.GetRecommendationFriendView;
import com.example.application.views.reviews.GiveReviewView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.component.notification.Notification;

import javax.annotation.security.PermitAll;
import java.util.*;
import java.util.List;


@PageTitle("MovieList")
@Route(value ="movieList" , layout = MainLayout.class)
@PermitAll

public class MovieListView extends Div {
    private final AuthenticatedUser authenticatedUser;

    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();

    ComboBox<Genre> selectGenre = new ComboBox<>();
    private final MovieService movieService;
    private final GenreService genreService;
    private final UserService userService;



    String siteRef = "http://localhost:8080/movieView/";
    public MovieListView(MovieService movieService, AuthenticatedUser authenticatedUser, GenreService genreService, UserService userService, MovieRepository movieRepository, UserRepository userRepository) {
        this.movieService = movieService;
        this.authenticatedUser = authenticatedUser;
        this.genreService = genreService;
        this.userService = userService;

        addClassName("movie-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        updateList();
    }



    private User getCurrentUser(){
        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return user;
        }
        return null;
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("title", "releaseDate");
        grid.addColumn(Movie::getLength).setHeader("Length");
        grid.addColumn(Movie::getGenreList).setHeader("Genre");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        //Button, inserts movie in WatchedList
        grid.addColumn(new ComponentRenderer<>(Button::new, (button, Movie) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e->
                    userService.insertWatchedList(getCurrentUser(), Movie));
            button.setIcon(new Icon(VaadinIcon.PLUS));
        })).setHeader("Watched");

        grid.addColumn(new ComponentRenderer<>(Button::new, (button, movie) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e->{
                UI.getCurrent().navigate(GiveReviewView.class,movie.getMovieID());
            });
            button.setIcon(new Icon(VaadinIcon.EDIT));
        })).setHeader("review");

        grid.addColumn(new ComponentRenderer<>(Button::new, (button, movie) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e->{
                UI.getCurrent().navigate(SendRecommendMovieView.class,movie.getMovieID());
            });
            button.setIcon(new Icon(VaadinIcon.GLASSES));
        })).setHeader("recommend to ...");

        //Button navigates to MovieView
        grid.addColumn(new ComponentRenderer<>(Button::new, (button, Movie) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e->
                    UI.getCurrent().navigate(siteRef + Movie.getMovieID()));
            button.setIcon(new Icon(VaadinIcon.ARROW_RIGHT));
        })).setHeader("See details");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));



        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        selectGenre.setItems(genreService.getGenre());
        selectGenre.setItemLabelGenerator(Genre::getGenre);
        selectGenre.addValueChangeListener(e -> updateListByGenre());

        HorizontalLayout toolbar = new HorizontalLayout(filterText,selectGenre, saveInWatchlistButton());
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    public  Component saveInWatchlistButton(){

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        Button button = new Button("Save in Watchlist",
                event -> {
                    List<Movie> movieSet = new ArrayList<>();
                    movieSet.addAll(grid.getSelectedItems());
                    userService.insertWatchList(authenticatedUser.get().get(), movieSet);

                    grid.deselectAll();

                });
    return button;
    }
    Button buttonToRecommendations = new Button("Go to Recommendations",
            event -> {
                UI.getCurrent().navigate(GetRecommendationFriendView.class);

            });





        private void updateList() {
        grid.setItems(movieService.findAllMoviesByTitle(filterText.getValue()));
    }
    private void updateListByGenre() {
        System.out.println(selectGenre.getValue().getGenre());

         grid.setItems(movieService.findAllMoviesByGenre(selectGenre.getValue()));
    }
}