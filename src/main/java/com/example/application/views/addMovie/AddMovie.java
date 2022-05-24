package com.example.application.views.addMovie;
import com.example.application.data.entity.Movie;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("AddMovie")
@Route(value = "addmovie", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class AddMovie extends Composite {

    @Override
    protected Component initContent(){
        TextField textFieldMovieId = new TextField("MovieID");
        TextField textFieldTitle = new TextField("Movie Title");
        TextField textFieldPosterSrc = new TextField("Postersrc");
        TextField textFieldreleaseDate = new TextField("Release date");
        TextField textFieldLength = new TextField("Movie length");
        Button buttonSaveMovie = new Button("Save Movie");



        VerticalLayout layout = new VerticalLayout(
                textFieldMovieId,
                textFieldTitle,
                textFieldPosterSrc,
                textFieldreleaseDate,
                textFieldLength);
                layout.add( new Button(
                        "Save Movie",
                        buttonClickEvent -> {
                            saveMovie(
                                textFieldMovieId.getValue(),
                                textFieldTitle.getValue(),
                                textFieldPosterSrc.getValue(),
                                Integer.parseInt(textFieldreleaseDate.getValue()),
                                Integer.parseInt(textFieldLength.getValue()));
                }));

        return layout;
    }


    public void saveMovie(String movieId, String title, String posterSrc, int releaseDate, int length){

    }

}
