package com.example.application.views.movielist;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.vaadin.data.Binder;
import com.vaadin.event.selection.MultiSelectionEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.MainLayout;
import javax.annotation.security.PermitAll;
import java.util.*;



@PageTitle("Movie List")
@Route(value ="" , layout = MainLayout.class)
@PermitAll

public class MovieListView extends VerticalLayout   {
    private AuthenticatedUser authenticatedUser;

    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();
    private final MovieService movieService;
    private final UserService userService;
    public MovieListView(MovieService movieService, AuthenticatedUser authenticatedUser, UserService userService) {
        this.movieService = movieService;
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        addClassName("movie-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), getContent());
        updateList();

    }


    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
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
        HorizontalLayout toolbar = new HorizontalLayout(filterText, saveInWatchlistButton());
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
                        if (!getCurrentUser().getWatchList().contains(movie)) {
                            userService.insertWatchList(getCurrentUser(), movie);
                        }
                    }


                });


    return button;
    }
        private void updateList() {
        grid.setItems(movieService.findAllMoviesByTitle(filterText.getValue()));
    }
}