package com.example.application.views.movie;


import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieRepository;
import com.example.application.data.service.MovieService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;


@PageTitle("Movie View")
@Route(value ="movieView" , layout = MainLayout.class)
@PermitAll
public class MovieView extends VerticalLayout implements HasUrlParameter<String> {

    private Movie displayedMovie;
    private Image poster;
    private TextField name = new TextField();
    private TextField genre = new TextField();
    private TextField writer = new TextField();
    private TextField cast = new TextField();
    private TextField releaseDate = new TextField();
    private TextField length = new TextField();
    private TextField director = new TextField();

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    private Button previousPage = new Button("See watched Movies");



    public MovieView (MovieService movieService, MovieRepository movieRepository){

        this.movieService = movieService;
        this.movieRepository = movieRepository;



    }

    private Component createTitle() {
        return new H3(displayedMovie.getTitle());
    }

    private void displayImage(){
        this.poster = new Image();
        poster.setSrc(displayedMovie.getPosterSrc());
    }

    private void fillTextFields(){
        this.name.setValue(displayedMovie.getTitle());
        //todo genre dosnt work
        this.genre.setValue(displayedMovie.getGenres());
        //todo fix this--------------------------------------------------------
        //this.writer.setValue(displayedMovie.getPersonAuthorList().toString());
        //this.cast.setValue(displayedMovie.getPersonCastList().toString());
        //this.director.setValue(displayedMovie.getPersonDirector());
        //todo ------------------------------------------------------------------------------------------
        this.releaseDate.setValue(String.valueOf(displayedMovie.getReleaseDate()));
        this.length.setValue(String.valueOf(displayedMovie.getLength()));

    }


    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        previousPage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousPage.addClickListener(e -> UI.getCurrent().navigate("http://localhost:8080/movieList"));
        return buttonLayout;
    }

     private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name, genre, length, releaseDate, writer, cast, director);
        return formLayout;
    }



    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String title) {
        if(title== null || title.isEmpty()){

            this.name.setValue("Fehler");
            this.genre.setValue("Fehler");
            this.writer.setValue(("Fehler"));
            this.cast.setValue(("Fehler"));
            this.director.setValue("Fehler");
            this.releaseDate.setValue("Fehler");
            this.length.setValue("Fehler");

        }
        else{
            this.displayedMovie = movieRepository.getById(Integer.valueOf(title));
            add(createTitle());
            add(createFormLayout());
            add(createButtonLayout());
            displayImage();
            fillTextFields();

        }
    }
}
