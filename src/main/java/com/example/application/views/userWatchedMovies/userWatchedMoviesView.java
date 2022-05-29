/*
package com.example.application.views.userWatchedMovies;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.data.service.MovieRepository;
import com.example.application.views.MainLayout;
import javax.annotation.security.PermitAll;


@PageTitle("Movie List")
@Route(value ="" , layout = MainLayout.class)
@PermitAll

public class userWatchedMoviesView extends VerticalLayout {
    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();
    private final MovieService movieService;

    public userWatchedMoviesView(MovieService movieService) {
        this.movieService = movieService;
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



    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("title", "releaseDate");
        grid.addColumn(movie -> movie.getTitle()).setHeader("title");
        grid.addColumn(movie -> movie.getReleaseDate()).setHeader("releaseDate");
        grid.addColumn(movie -> movie.getGenreList()).setHeader("genre");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addMovieButton = new Button("Untag Movie");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addMovieButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private void updateList() {
        grid.setItems(movieService.findAllMoviesByTitle(filterText.getValue()));
    }
}

*/

