package com.example.application.views.watchlist;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.security.PermitAll;
import java.util.HashSet;
import java.util.Set;

@PageTitle("Watchlist")
@Route(value = "watchlist", layout = MainLayout.class)
@PermitAll
public class Watchlist extends Div {


    private final AuthenticatedUser authenticatedUser;
    private final MovieService movieService;
    private final UserService userService;


    @Autowired
    public Watchlist(AuthenticatedUser authenticatedUser, MovieService movieService, UserService userService) {
        this.authenticatedUser = authenticatedUser;
        this.movieService = movieService;
        this.userService = userService;

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

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());


        HorizontalLayout toolbar = new HorizontalLayout(filterText, DeleteFromWatchlistButtonPlusGrid());
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    public Component DeleteFromWatchlistButtonPlusGrid(){
        Set<Movie> movieSet = new HashSet<>();

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            movieSet.addAll(event.getAllSelectedItems());
        });

        Button button = new Button("Delete from Watchlist",
                event -> {
                    for (Movie movie : movieSet) {
                        userService.deleteWatchlist(authenticatedUser.get().get(), movie);

                    }


                });


        return button;
    }

    private void updateList() {
        grid.setItems(authenticatedUser.get().get().getWatchList());
    }

    Grid<Movie> grid = new Grid<>(Movie.class);
    TextField filterText = new TextField();
}
