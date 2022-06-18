package com.example.application.views.RecommendedView;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.RecommenedService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("RecommendedMovies")
@Route(value = "image-list", layout = MainLayout.class)
public class RecommendedView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;
    private final RecommenedService recommenedService;


    public RecommendedView(RecommenedService recommenedService, MovieService movieService, AuthenticatedUser authenticatedUser) {

        this.recommenedService = recommenedService;
        constructUI();

        for(Movie movie: recommenedService.getRecommendedMovies()) {
            imageContainer.add(new MovieListViewCard(movie.getTitle(),
                    movie.getPosterSrc(), movie.getGenreList().toString()));
        }




    }

    private void constructUI() {
        addClassNames("image-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Beautiful photos");
        header.addClassNames("mb-0", "mt-xl", "text-3xl");
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames("mb-xl", "mt-0", "text-secondary");
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        imageContainer = new OrderedList();
        imageContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        container.add(header, sortBy);
        add(container, imageContainer);

    }
}