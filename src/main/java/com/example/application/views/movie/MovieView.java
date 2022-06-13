package com.example.application.views.movie;


import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieRepository;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.awt.*;

@PageTitle("MovieView")
@Route(value ="movieView" , layout = MainLayout.class)
@PermitAll
public class MovieView implements HasUrlParameter<String> {

    private Movie displayedMovie;
    private Image poster;
    private TextField name;
    private TextField genre;
    private TextField writer;
    private TextField cast;
    private TextField releaseDate;
    private TextField length;
    private TextField director;

    private Button previousPage = new Button("See watched Movies");



    public MovieView (MovieRepository movieRepository){
        createTitle();
        displayImage();
        createFormLayout();
        createButtonLayout();

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
        this.genre.setValue(displayedMovie.getGenres());
        //todo check if toString() actually works----------------------------------------------------------
        this.writer.setValue(displayedMovie.getPersonAuthorList().toString());
        this.cast.setValue(displayedMovie.getPersonCastList().toString());
        //todo ------------------------------------------------------------------------------------------
        this.director.setValue(displayedMovie.getPersonDirector());
        this.releaseDate.setValue(String.valueOf(displayedMovie.getReleaseDate()));
        this.length.setValue(String.valueOf(displayedMovie.getLength()));
    }


    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        previousPage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousPage.addClickListener(e -> UI.getCurrent().navigate("http://localhost:8080/MovieList"));
        return buttonLayout;
    }

     private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name, genre, length, releaseDate, writer, cast, director);
        return formLayout;
    }



    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        if(parameter == null){
            UI.getCurrent().navigate("http://localhost:8080/MovieList");
        }
        else{
            displayImage();
            fillTextFields();
            createFormLayout();
        }
    }
}
