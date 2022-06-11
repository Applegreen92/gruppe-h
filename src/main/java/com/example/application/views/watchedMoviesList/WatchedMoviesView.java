package com.example.application.views.watchedMoviesList;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;
import java.util.Optional;

@PageTitle("watchedMovies")
@Route(value ="watchedMovies" , layout = MainLayout.class)
@PermitAll
public class WatchedMoviesView extends VerticalLayout implements HasUrlParameter<String> {
    private AuthenticatedUser authenticatedUser;
    private User user;

    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();
    private final MovieService movieService;
    private final UserService userService;
    String site = "http://localhost:8080/";

    public WatchedMoviesView(MovieService movieService, AuthenticatedUser authenticatedUser, UserService userService) {
        this.movieService = movieService;
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.user = authenticatedUser.get().get();
        addClassName("movie-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        getWatchedMovies();
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

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
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

    private void updateList() {
        grid.setItems(movieService.findAllMoviesByTitle(filterText.getValue()));
    }

    private void getWatchedMovies(User user){
        grid.setItems(userService.findByUsername(user.getUsername()).getWatchedMovies());
    }
    private void getWatchedMovies(){
        grid.setItems(userService.findByUsername(user.getUsername()).getWatchedMovies());

    }
    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String userName) {
        if(userName == null) {
        }
        else {
            user = userService.findByUsername(userName);
            getWatchedMovies(user);

        }
    }
}

