package com.example.application.views.movielist;

import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.GenreService;
import com.example.application.data.service.MovieRepository;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.server.Page;
import com.vaadin.flow.component.notification.Notification;

import javax.annotation.security.PermitAll;
import java.util.HashSet;
import java.util.Objects;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@PageTitle("MovieList")
@Route(value ="" , layout = MainLayout.class)
@PermitAll

public class    MovieListView extends Div {
    private final AuthenticatedUser authenticatedUser;

    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();

    ComboBox<Genre> selectGenre = new ComboBox<>();
    private final MovieService movieService;
    private final GenreService genreService;
    private final UserService userService;
    private final MovieRepository movieRepository;
    public MovieListView(MovieService movieService, AuthenticatedUser authenticatedUser, GenreService genreService, UserService userService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.authenticatedUser = authenticatedUser;
        this.genreService = genreService;
        this.userService = userService;
        this.movieRepository = movieRepository;
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
//        grid.addColumn("genre");
//        grid.removeColumnByKey("movieID");

        //grid.setItems(movieRepository.findAll());
        //grid.addColumn(Movie::getTitle).setHeader("Title");
        //grid.addColumn(Movie::getReleaseDate).setHeader("Release Date");
        grid.addColumn(Movie::getLength).setHeader("Length");
        grid.addColumn(Movie::getGenreList).setHeader("Genre");
//        grid.addColumn(Movie::getGenres).setHeader("Genre");

        //grid.setItems(movieRepository.findAll());

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addColumn(new ComponentRenderer<>(Button::new, (button, Movie) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e->
                    userService.insertWatchedList(getCurrentUser(), Movie));
            button.setIcon(new Icon(VaadinIcon.PLUS));
        })).setHeader("Tag Movie as Watched");
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
        Set<Movie> movieSet = new HashSet<>();

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            movieSet.addAll(event.getAllSelectedItems());
        });

        Button button = new Button("Save in Watchlist",
                event -> {
                    for (Movie movie : movieSet) {
                            String response = userService.insertWatchList(Objects.requireNonNull(getCurrentUser()), movie);
                            Notification.show(response);
                            grid.deselectAll();

                    }


                });


    return button;
    }
        private void updateList() {
        grid.setItems(movieService.findAllMoviesByTitle(filterText.getValue()));
    }
    private void updateListByGenre() {
        System.out.println(selectGenre.getValue().getGenre());

         grid.setItems(movieService.findAllMoviesByGenre(selectGenre.getValue()));
    }
}