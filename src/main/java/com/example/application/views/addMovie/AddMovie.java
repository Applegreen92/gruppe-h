package com.example.application.views.addMovie;
import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.io.IOException;


@PageTitle("AddMovie")
@Route(value = "addMovie", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class AddMovie extends Div {

    public AddMovie(MovieService movieService){
        add(saveMovieLayout());
        this.movieService = movieService;
    }

    private final MovieService movieService;


    protected Component saveMovieLayout(){

        // Textfields wich defines a Movie Object
        TextField textFieldMovieId = new TextField("MovieID");
        TextField textFieldTitle = new TextField("Movie Title");
        TextField textFieldPosterSrc = new TextField("Postersrc");
        TextField textFieldreleaseDate = new TextField("Release date");
        TextField textFieldLength = new TextField("Movie length");

        VerticalLayout layout = new VerticalLayout();

        //Add Textfields to the layout
        layout.add(
                textFieldMovieId,
                textFieldTitle,
                textFieldPosterSrc,
                textFieldreleaseDate,
                textFieldLength);
        // WIth this Button you put the Movie Object into the DB

        layout.add(new Button("Save Movie",
                buttonClickEvent -> {
                    try {
                        saveMovieInDatabase(

                                Integer.valueOf(textFieldMovieId.getValue()),
                                textFieldTitle.getValue(),
                                textFieldPosterSrc.getValue(),
                                Integer.valueOf(textFieldreleaseDate.getValue()),
                                Integer.valueOf(textFieldLength.getValue()));

                    } catch (IOException | InterruptedException e) {
                        System.out.println("Could not put Movie into Database");
                        throw new RuntimeException(e);
                    }
                }));


        return layout;
    }




    public void saveMovieInDatabase(int movieId, String title, String posterSrc, int releaseDate, int length) throws IOException, InterruptedException {
        Movie movie = new Movie(movieId,title,posterSrc,releaseDate,length);
        movieService.addNewMovie(movie);





    }

}
