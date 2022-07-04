package com.example.application.views.stats;

import com.example.application.data.entity.*;
import com.example.application.data.service.*;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@PageTitle("User Stats")
@Route(value ="userStats" , layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class UserStatsView extends VerticalLayout implements HasUrlParameter<String> {
    private AuthenticatedUser authenticatedUser;
    private User user;
    private TextField totalWatchtime = new TextField("Total Watchtime");
    private TextField favActor = new TextField("Favourite Actor");
    private TextField favMovie = new TextField("Favourite Movie");
    private TextField favGenre = new TextField("Favourite Genre");
    private Button resetStats = new Button("Reset Stats", new Icon(VaadinIcon.REFRESH));
    private PersonRepository personRepository;
    private UserService userService;
    private List<MoviePersonPartLink> displayedPersonCast;
    private final MovieRepository movieRepository;
    private final MoviePersonPartLinkRepository moviePersonPartLinkRepository;
    private final PartRepository partRepository;
    private final GenreRepository genreRepository;


    public UserStatsView(AuthenticatedUser authenticatedUser, PersonRepository personRepository, UserService userService, MovieRepository movieRepository, MoviePersonPartLinkRepository moviePersonPartLinkRepository, PartRepository partRepository, GenreRepository genreRepository){

        this.authenticatedUser = authenticatedUser;
        this.personRepository = personRepository;
        this.userService = userService;
        this.user = authenticatedUser.get().get();
        this.movieRepository = movieRepository;
        this.moviePersonPartLinkRepository = moviePersonPartLinkRepository;
        this.partRepository = partRepository;
        this.genreRepository = genreRepository;

        updateData();
    }

    private Component createTitle() {
        return new H3("Stats for: " + user.getUsername());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(totalWatchtime, favActor, favMovie, favGenre);
        return formLayout;
    }

   public int getTotalWatchTime(){
        int watchTime = 0;
        for(Movie movie: this.user.getWatchedMovies()){
            watchTime += movie.getLength();
        }
        return watchTime;
   }

   public void updateData(){
       totalWatchtime.setValue((String.valueOf(getTotalWatchTime())));
       favGenre.setValue(getFavouriteGenre().getGenre());
       //favActor.setValue(getFavouriteActor());
       favMovie.setValue(getFavouriteMovie());

   }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        /*resetStats.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        //todo: RESET BUTTON hat noch keine Funktion. Soll aber später die Tables die angsprochen werden clearen.
        resetStats.addClickListener(e-> {
            movieService.deleteMovieReviews(displayedMovie);
            UI.getCurrent().getPage().reload();
        });
        */buttonLayout.add(resetStats);
        return buttonLayout;
    }

    public Genre getFavouriteGenre(){

        List<Movie> copyWatchedList;
        List<Genre> genreList = genreRepository.findAll();
        int[] genreCounts =new int[genreList.size()];
        int mostViewedGenreInt = 0;
        int mostviewedGenreIndex = 0;
        if(this.user.getWatchedMovies().isEmpty()){
            return new Genre("No favourite found");
        }
        copyWatchedList = this.user.getWatchedMovies();


        for (Movie movie: copyWatchedList) {
            for (Genre genre: movie.getGenreList()) {
                for(int i= 0; i<genreList.size(); i++) {
                    if(genre.getGenre().equals(genreList.get(i).getGenre())) {
                        genreCounts[i]++;
                    };
                }
            }
        }
        for(int i= 0; i<genreCounts.length; i++) {
            if (genreCounts[i] > mostViewedGenreInt) {
                mostViewedGenreInt = genreCounts[i];
                mostviewedGenreIndex = i;
            }
        }
        return genreList.get(mostviewedGenreIndex);
    }

    public String getFavouriteActor(){
        List<Movie> copyWatchedList;
        ArrayList<Person> actorList = new ArrayList<>();

        if(this.user.getWatchedMovies().isEmpty()){
            Person empty = new Person();
            empty.setFirstname("No Favourite found");
            return empty.getFirstname();
        }
        for(Movie movie: user.getWatchedMovies()){
            if(!movie.getPersonCastList().isEmpty()) {
                this.displayedPersonCast = moviePersonPartLinkRepository.findAllPersonsByMovieAndPart(movie,partRepository.getById(3));
                for(int i = 0; i < displayedPersonCast.size(); i++) {
                    actorList.add(displayedPersonCast.get(i).getPerson());
                }
            }
        }
        int[] actorCounts =new int[user.getWatchedMovies().size()*3];
        int mostViewedActorInt = 0;
        int mostviewedActorIndex = 0;
        copyWatchedList = this.user.getWatchedMovies();
        for (Movie movie: copyWatchedList) {
            for (Person person: movie.getPersonCastList()) {
                for(int i= 0; i < actorList.size(); i++) {
                    if((person.getFirstname().equals(actorList.get(i).getFirstname())) && person.getLastname().equals(actorList.get(i).getLastname())) {
                        actorCounts[i]++;
                    };
                }
            }
        }
        for(int i= 0; i<actorCounts.length; i++) {
            if (actorCounts[i] > mostViewedActorInt) {
                mostViewedActorInt = actorCounts[i];
                mostviewedActorIndex = i;
            }
        }
        return actorList.get(mostviewedActorIndex).getFirstname() + " " + actorList.get(mostviewedActorIndex).getLastname();
    }




    public String getFavouriteMovie(){

        HashMap<Review, Movie> reviewMap = new HashMap<>();
        List<Movie> watchedMovies = user.getWatchedMovies();
        List<Review> reviewList = new ArrayList<>();
        List<Review> userReviewList = new ArrayList<>();
        int mostViewedReviewInt = 0;
        int mostViewedReviewIndex = 0;

        for(Movie movie: watchedMovies){
            reviewList.addAll(movie.getReviewList());
            for(Review review: reviewList){
                if(review.getUserID() == this.user.getId()){
                    if(!userReviewList.contains(review)) {
                        userReviewList.add(review);
                        reviewMap.put(review, movie);
                    }
                }
            }
        }
        if(userReviewList.size() == 0){
            return "No data available";
        }
        for(int i= 0; i <userReviewList.size(); i++) {
            if (userReviewList.get(i).getStarReviewOntToFive() > mostViewedReviewInt) {
                mostViewedReviewInt = userReviewList.get(i).getStarReviewOntToFive();
                mostViewedReviewIndex = i;
            }
        }
        return reviewMap.get(userReviewList.get(mostViewedReviewIndex)).getTitle();
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
        String name = parameter.substring(9);
        user = userService.findByUsername(name);
        add(createTitle());
        add(createFormLayout());
        updateData();

    }
}
