package com.example.application.views.reviews;



import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Review;
import com.example.application.data.service.MovieRepository;

import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import javax.annotation.security.PermitAll;
import java.util.List;


@PageTitle("Give Review")
@Route(value = "give-review",layout = MainLayout.class)
@PermitAll
public class GiveReviewView extends Div implements HasUrlParameter<Integer> {

    private final AuthenticatedUser authenticatedUser;
    private final MovieRepository movieRepository;
    private Movie movie;



    Select<Integer> rating = new Select<>(1,2,3,4,5);
    TextArea newReviewText= new TextArea();
    public Component giveReview(){
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button deleteReviewButton = new Button("Delete review");
        Button insertReviewButton = new Button("save/change review");

        newReviewText.setWidth("800px");

        newReviewText.setHeight("200px");
        newReviewText.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + 600);
        });
        newReviewText.setLabel("Here you can give a review to the Movie "+movie.getTitle());
        newReviewText.setMaxLength(600);

        rating.setPlaceholder("Select the rating from 1 to 5");

        insertReviewButton.addClickListener(event -> {


            for(int i = 0; i < movie.getReviewList().size();i++){
                Review review = movie.getReviewList().get(i);

                //Checks if user allready has a review so it will be just changed
                if(review.getUserID() == authenticatedUser.get().get().getId()){
                    movie.getReviewList().get(i).setUserReview(newReviewText.getValue());
                    if(rating.getValue() != null){
                        movie.getReviewList().get(i).setStarReviewOntToFive((rating.getValue()));}
                    movieRepository.save(movie);
                    UI.getCurrent().getPage().reload();
                    return;
                }
            }
            //No review means no review
            movie.getReviewList().add(new Review(newReviewText.getValue(),rating.getValue(),authenticatedUser.get().get().getId()));
            movieRepository.save(movie);
            UI.getCurrent().getPage().reload();

        });
        deleteReviewButton.addClickListener(event -> {
           for(int i = 0; i < movie.getReviewList().size();i++){
               Review review = movie.getReviewList().get(i);
               if( review.getUserID() == authenticatedUser.get().get().getId()){
                   movie.getReviewList().remove(i);
                   movieRepository.save(movie);
                   Notification.show("Review successfully deleted");
                   UI.getCurrent().getPage().reload();

                   return;
               }
           }
           Notification.show("You dont have a review to delete");
            UI.getCurrent().getPage().reload();
        });


        horizontalLayout.add(insertReviewButton,deleteReviewButton);
        verticalLayout.add(newReviewText,rating,horizontalLayout);

        return verticalLayout;
    }




    @Autowired
    public GiveReviewView(AuthenticatedUser authenticatedUser, MovieRepository movieRepository) {
        this.authenticatedUser = authenticatedUser;
        this.movieRepository = movieRepository;


    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        if(parameter == null){
            System.out.println("Still don't work");
        }else {
            List<Movie> movieList = movieRepository.findAll();
            for (Movie movie : movieList) {
                if (parameter == movie.getMovieID()) {
                    this.movie = movie;
                    break;
                }
            }
            VerticalLayout ve = new VerticalLayout();


            add(giveReview());
            if (!movie.getReviewList().isEmpty()) {
                for (Review review : movie.getReviewList()){

                    TextArea text = new TextArea();
                    TextField stars = new TextField();
                    stars.addThemeVariants(TextFieldVariant.LUMO_SMALL);
                    stars.setValue(String.valueOf(review.getStarReviewOntToFive())+" stars rating");
                    stars.setReadOnly(true);
                    text.setReadOnly(true);
                    text.setValue(review.getUserReview());
                    text.setWidth("800px");
                    text.setHeight("100px");
                    if(review.getUserID() == authenticatedUser.get().get().getId()){
                        newReviewText.setValue(review.getUserReview());
                        rating.setEmptySelectionAllowed(true);
                        rating.setEmptySelectionCaption("old rating is "+review.getStarReviewOntToFive());
                    }


                    ve.add(text);
                    ve.add(stars);



                }

            }
            add(ve);
        }

    }
}
