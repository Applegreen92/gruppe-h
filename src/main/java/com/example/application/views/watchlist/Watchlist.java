package com.example.application.views.watchlist;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.example.application.views.MovieFriendRecommend.GetRecommendationFriendView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.security.PermitAll;
import java.util.*;


@PageTitle("Watchlist")
@Route(value = "watchlist", layout = MainLayout.class)
@PermitAll
public class Watchlist extends Div {


    private final AuthenticatedUser authenticatedUser;
    private final MovieService movieService;
    private final UserService userService;
    Grid<Movie> grid = new Grid<>(Movie.class);



   /* private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }*/
    TextField filterText = new TextField();

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

        Button button = new Button("Friend Recommendations");
        button.addClickListener(event -> {
           UI.getCurrent().navigate(GetRecommendationFriendView.class);
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, DeleteFromWatchlistButtonPlusGrid(),button);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public Component DeleteFromWatchlistButtonPlusGrid(){
        List<Movie> movieSet = new ArrayList<>();

        grid.setSelectionMode(Grid.SelectionMode.MULTI);


        Button button = new Button("Delete from Watchlist",
                event -> {

                    movieSet.addAll(grid.getSelectedItems());
                    userService.deleteWatchlist(authenticatedUser.get().get(), movieSet);
                    grid.deselectAll();
                    UI.getCurrent().getPage().reload();




                    Notification.show("Movie/s successfully deleted.");
        });


        return button;
    }

    private void updateList() {
        grid.setItems(authenticatedUser.get().get().getWatchList());
    }
}
