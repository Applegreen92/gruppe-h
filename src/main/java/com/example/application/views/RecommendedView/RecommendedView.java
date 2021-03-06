package com.example.application.views.RecommendedView;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.RecommendedService;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Recommended Movies")
@Route(value = "recommendedmovies", layout = MainLayout.class)
@PermitAll
public class RecommendedView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;
    private final UserService userService;
    private final AuthenticatedUser authenticatedUser;
    private final RecommendedService recommendedService;

    private List<Movie> recommendedMovieList = new ArrayList<>();

    public RecommendedView(UserService userService, AuthenticatedUser authenticatedUser, RecommendedService recommendedService) {
        this.userService = userService;
        this.authenticatedUser = authenticatedUser;
        constructUI();
        this.recommendedService = recommendedService;

//        recommenedMovieList.addAll(recommenedService.getRecommendedMovies());
//        for(int i = 0; i < recommenedMovieList.size(); i++){
//            imageContainer.add(new MovieListViewCard(recommenedMovieList.get(i).getTitle(),recommenedMovieList.get(i).getReleaseDate(),
//                    recommenedMovieList.get(i).getPosterSrc(), recommenedMovieList.get(i).getGenreList().toString()));
//            i++;
//            imageContainer.add(new MovieListViewCard(recommenedMovieList.get(i).getTitle(),recommenedMovieList.get(i).getReleaseDate(),
//                    recommenedMovieList.get(i).getPosterSrc(), recommenedMovieList.get(i).getGenreList().toString()));
//            i++;
//            imageContainer.add(new MovieListViewCard(recommenedMovieList.get(i).getTitle(),recommenedMovieList.get(i).getReleaseDate(),
//                    recommenedMovieList.get(i).getPosterSrc(), recommenedMovieList.get(i).getGenreList().toString()));
//        }

        for(Movie movie: recommendedService.getRecommendedMovies()) {
            imageContainer.add(new MovieListViewCard(movie.getTitle(),movie.getReleaseDate(),
                    movie.getPosterSrc(), movie.getGenreList().toString(), movie, this.userService, this.authenticatedUser));
        }


    }

    private void constructUI() {
        addClassNames("image-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        imageContainer = new OrderedList();

        imageContainer.addClassNames("gap-m", "grid", "list-none", "p-0");
        add(imageContainer);
//        addClassNames("image-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");
//
//        HorizontalLayout container = new HorizontalLayout();
//        container.addClassNames("items-center", "justify-between");
//
//        VerticalLayout headerContainer = new VerticalLayout();
//        //H2 header = new H2("Beautiful photos");
////        header.addClassNames("mb-0", "mt-xl", "text-3xl");
////        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
////        description.addClassNames("mb-xl", "mt-0", "text-secondary");
//        //headerContainer.add(header, description);
//
////        Select<String> sortBy = new Select<>();
////        sortBy.setLabel("Sort by");
////        sortBy.setItems("Popularity", "Newest first", "Oldest first");
////        sortBy.setValue("Popularity");
//
//        imageContainer = new OrderedList();
//        imageContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");
//
//        //container.add(header, sortBy);
//        add(container, imageContainer);

    }
}