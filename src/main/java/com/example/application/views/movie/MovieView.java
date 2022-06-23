package com.example.application.views.movie;


import com.example.application.data.entity.Movie;
import com.example.application.data.entity.MoviePersonPartLink;
import com.example.application.data.entity.Part;
import com.example.application.data.service.MoviePersonPartLinkRepository;
import com.example.application.data.service.MovieRepository;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.PartRepository;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;
import java.util.List;


@PageTitle("Movie View")
@Route(value ="movieView" , layout = MainLayout.class)
@PermitAll
public class MovieView extends VerticalLayout implements HasUrlParameter<String> {

    private Movie displayedMovie;
    private Part author;
    private Part castPart;
    private Part directorPart;
    private List<MoviePersonPartLink> displayedPersonDirector;
    private List<MoviePersonPartLink> displayedPersonAuthor;
    private List<MoviePersonPartLink> displayedPersonCast;
    private String authors;
    private String casts;
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
    private final MoviePersonPartLinkRepository moviePersonPartLinkRepository;
    private final PartRepository partRepository;

    private Button previousPage = new Button("See watched Movies");
    private Button seeAdminStats = new Button("See Stats", new Icon(VaadinIcon.LINE_BAR_CHART));

    String siteRef = "http://localhost:8080/adminStats/";



    public MovieView (MovieService movieService, MovieRepository movieRepository, MoviePersonPartLinkRepository moviePersonPartLinkRepository, PartRepository partRepository){

        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.moviePersonPartLinkRepository = moviePersonPartLinkRepository;
        this.partRepository = partRepository;
    }

    private Component createTitle() {
        return new H3(displayedMovie.getTitle());
    }

    private void displayImage(){
        this.poster = new Image();
        poster.setSrc(displayedMovie.getPosterSrc());
        add(poster);
    }

    public void labelTextfields(){
        name.setLabel("Title");
        genre.setLabel("Genre");
        writer.setLabel("Writer");
        cast.setLabel("Cast");
        releaseDate.setLabel("Release year");
        length.setLabel("Runtime in minutes");
        director.setLabel("Director");
    }

    private void fillTextFields(){
        this.name.setValue(displayedMovie.getTitle());
        //todo genre does work
        this.genre.setValue(displayedMovie.getGenreList().toString());
        //todo fix this--------------------------------------------------------
        for (int i = 0; i < displayedPersonAuthor.size(); i++) {
            if(authors == null){
                authors = displayedPersonAuthor.get(i).getPerson().getFirstname() + " " + displayedPersonAuthor.get(i).getPerson().getLastname();
            }else {
                authors = authors + ", " + displayedPersonAuthor.get(i).getPerson().getFirstname() + " " + displayedPersonAuthor.get(i).getPerson().getLastname();
            }
        }
        this.writer.setValue(authors);
        for (int i = 0; i < displayedPersonCast.size(); i++) {
            if(casts == null){
                casts = displayedPersonCast.get(i).getPerson().getFirstname() + " " + displayedPersonCast.get(i).getPerson().getLastname();
            }else {
                casts = casts + ", " + displayedPersonCast.get(i).getPerson().getFirstname() + " " + displayedPersonCast.get(i).getPerson().getLastname();
            }
        }
        this.cast.setValue(casts);
        this.director.setValue(displayedPersonDirector.get(0).getPerson().getFirstname() + " " + displayedPersonDirector.get(0).getPerson().getLastname());

        this.releaseDate.setValue(String.valueOf(displayedMovie.getReleaseDate()));
        this.length.setValue(String.valueOf(displayedMovie.getLength()));

    }


    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        //previousPage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //previousPage.addClickListener(e -> UI.getCurrent().navigate("http://localhost:8080/movieList"));
        seeAdminStats.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        seeAdminStats.addClickListener(e -> UI.getCurrent().navigate(siteRef + displayedMovie.getMovieID()));
        buttonLayout.add(seeAdminStats);
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
            this.directorPart = partRepository.getById(1);
            this.author = partRepository.getById(2);
            this.castPart = partRepository.getById(3);
            this.displayedPersonDirector = moviePersonPartLinkRepository.findAllPersonsByMovieAndPart(displayedMovie,directorPart);
            this.displayedPersonAuthor = moviePersonPartLinkRepository.findAllPersonsByMovieAndPart(displayedMovie,author);
            this.displayedPersonCast = moviePersonPartLinkRepository.findAllPersonsByMovieAndPart(displayedMovie,castPart);
            add(createTitle());
            displayImage();
            add(createFormLayout());
            add(createButtonLayout());
            fillTextFields();
            labelTextfields();

        }
    }
}
