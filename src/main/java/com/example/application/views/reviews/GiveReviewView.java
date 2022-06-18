package com.example.application.views.reviews;



import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Review;
import com.example.application.data.service.MovieRepository;

import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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



    public Component showReviews(){
        VirtualList<Review> list = new VirtualList<>();
        list.setItems(movie.getReviewList());
        list.setRenderer(reviewCardRenderer);


        return list;
    }


    public Component giveReview(){
        VerticalLayout verticalLayout = new VerticalLayout();

        Button button = new Button("save review");
        Select<Integer> rating = new Select<>(1,2,3,4,5);
        TextArea textArea= new TextArea();

        textArea.setWidth("800px");
        textArea.setHeight("200px");
        textArea.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + 600);
        });
        textArea.setLabel("Here you can give a review");
        textArea.setMaxLength(600);

        rating.setPlaceholder("Select the rating from 1 to 5");

        button.addClickListener(event -> {
            movie.getReviewList().add(new Review("as",1));


        });
        verticalLayout.add(textArea,rating,button);

        return verticalLayout;
    }




    public ComponentRenderer<Component, Review> reviewCardRenderer = new ComponentRenderer<>(review -> {
        VerticalLayout cardLayout = new VerticalLayout();


        Avatar avatar = new Avatar();
        avatar.setHeight("64px");
        avatar.setWidth("64px");
        avatar.setImage(movie.getPosterSrc());
        avatar.setName(movie.getTitle());

//        VerticalLayout infoLayout = new VerticalLayout();
//        infoLayout.setSpacing(false);
//        infoLayout.setPadding(false);
//        infoLayout.add(movie.getTitle());

        VerticalLayout reviewLayout = new VerticalLayout();
        reviewLayout.add(new Div(new Text(String.valueOf(review.getStarReviewOntToFive()))));
        reviewLayout.add(new Div(new Text(review.getUserReview())));


        return cardLayout;
    });



    @Autowired
    public GiveReviewView(AuthenticatedUser authenticatedUser, MovieRepository movieRepository) {
        this.authenticatedUser = authenticatedUser;
        this.movieRepository = movieRepository;


    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        if(parameter == null){
            System.out.println("Still don't work");
        }else{
            List<Movie> movieList = movieRepository.findAll();
            for(Movie movie : movieList){
                if(parameter == movie.getMovieID()){
                    this.movie = movie;
                    break;
                }
            }

            add(giveReview());
            //add(showReviews());

        }


    }
}
