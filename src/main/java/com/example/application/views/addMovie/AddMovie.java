package com.example.application.views.addMovie;
import com.example.application.data.entity.Genre;
import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Person;
import com.example.application.data.service.MovieService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.util.ArrayList;


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

    private final ArrayList<Genre> genreList= new ArrayList();
    private final ArrayList<String> authorList= new ArrayList();
    private final ArrayList<String> castList= new ArrayList();

    protected Component saveMovieLayout(){
        // Textfields wich defines a Movie Object


        VerticalLayout layout = new VerticalLayout();
        //Add Textfields to the layout
        layout.add(textFieldMovieId,
                textFieldTitle,
                textFieldPosterSrc,
                textFieldreleaseDate,
                textFieldLength,
                textFieldDirector
                );

        layout.add(saveGenreComponent());
        layout.add(saveAuthorComponent());
        layout.add(saveCastComponent());
        layout.add(movieIntoDatabaseButton);





        return layout;


    }
    protected Component saveGenreComponent(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(textFieldGenre);
        layout.add(saveGenreButton);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        return layout;
    }
    protected Component saveAuthorComponent(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(textFieldAuthor);
        layout.add(saveAuthorButton);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        return layout;
    }
    protected Component saveCastComponent(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(textFieldCast);
        layout.add(saveCastButton);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        return layout;
    }
    public void saveMovieInDatabase(String movieId, String title, String posterSrc, String releaseDate, String length,String personDirector) throws IOException, InterruptedException {

        // Input Error handling

        if(releaseDate.isEmpty()){
            Notification.show("Please type in a Releasedate");
        }else {
            try {
                int releasdateCheck = Integer.parseInt(releaseDate);
            }catch(NumberFormatException e){
                Notification.show("Releasedate has to be an Integer");
            }
        }
        if(length.isEmpty()){
            Notification.show("Please type in the Movielength");
        }else{
            try {
                int lengthCheck = Integer.parseInt(length);
            }catch(NumberFormatException e){
                Notification.show("Movielength has to be an Integer");
            }
        }
        if (getTitle().isEmpty()) {
            Notification.show("Title must be of Type String");
            return;
        } else if (posterSrc.isEmpty()) {
            Notification.show("Poster source must be of Type String");
            return;
        } else if(personDirector.isEmpty()){
            Notification.show("Director must be of type String");
        } else if (this.genreList.isEmpty() && this.textFieldGenre.isEmpty()){
            Notification.show("Choose at least 1 Genre");
            return;
        }else if(this.authorList.isEmpty() && this.textFieldAuthor.isEmpty()){
            Notification.show("Choose at least 1 Author");
            return;
        }else if(this.castList.isEmpty() && this.textFieldCast.isEmpty()){
            Notification.show("Choose at least 1 cast member");
            return;
        }else{

            //Movie movie = new Movie(title,posterSrc,Integer.valueOf(releaseDate),Integer.valueOf(length),personDirector,this.genreList,this.authorList,this.castList);
            //movieService.addNewMovie(movie);
            Notification.show("Movie Successfully saved");
        }
    }


    // --------------Text fields start--------------------
        TextField textFieldMovieId = new TextField("MovieID");
    TextField textFieldTitle = new TextField("Movie Title");
    TextField textFieldPosterSrc = new TextField("Postersrc");
    TextField textFieldreleaseDate = new TextField("Release date");
    TextField textFieldLength = new TextField("Movie length");
    TextField textFieldDirector = new TextField("Movie Director");
    TextField textFieldGenre = new TextField("Genres");
    TextField textFieldAuthor = new TextField("Authors");
    TextField textFieldCast = new TextField("Cast");
    // --------------Text fields end--------------------

    // --------------Buttons start--------------------
    Button saveGenreButton = new Button("Save Genre",
            buttonClickEvent -> {
                this.genreList.add(new Genre(textFieldGenre.getValue()));
                textFieldGenre.clear();
            });
    Button saveAuthorButton = new Button("Save Author",
            buttonClickEvent -> {
                this.authorList.add(textFieldAuthor.getValue());
                textFieldAuthor.clear();
            });
    Button saveCastButton = new Button("Save Cast",
            buttonClickEvent ->{
                this.castList.add(textFieldCast.getValue());
                textFieldCast.clear();
            });
    Button movieIntoDatabaseButton = new Button("Save Movie",
            buttonClickEvent -> {
                if(!textFieldGenre.isEmpty()){
                    this.genreList.add(new Genre(textFieldGenre.getValue()));
                }
                if(!textFieldAuthor.isEmpty()){
                    this.authorList.add(textFieldAuthor.getValue());
                }
                if(!textFieldCast.isEmpty()){
                    this.castList.add(textFieldCast.getValue());
                }


                try {
                    saveMovieInDatabase(
                            textFieldMovieId.getValue(),
                            textFieldTitle.getValue(),
                            textFieldPosterSrc.getValue(),
                            textFieldreleaseDate.getValue(),
                            textFieldLength.getValue(),
                            textFieldDirector.getValue()
                    );

                } catch (IOException | InterruptedException e) {
                    System.out.println("Could not put Movie into Database");
                    throw new RuntimeException(e);
                }
            });
    // --------------Buttons end--------------------
}
