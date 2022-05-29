package com.example.application.views.watchlist;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;

@PageTitle("Watchlist")
@Route(value = "watchlist", layout = MainLayout.class)
@PermitAll
public class Watchlist extends Div {

    private final MovieService movieService;

    public Watchlist(MovieService movieService) {
        this.movieService = movieService;
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        updateList();

    }


   /* private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }*/



    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("title", "releaseDate");
        grid.addColumn(movie -> movie.getTitle()).setHeader("title");
        grid.addColumn(movie -> movie.getReleaseDate()).setHeader("releaseDate");
        //grid.addColumn(movie -> movie.getGenreList()).setHeader("genre");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addMovieButton = new Button("Delete Movie");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addMovieButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(movieService.findAllMoviesByTitle(filterText.getValue()));
    }

    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();
}
