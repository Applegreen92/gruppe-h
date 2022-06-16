package com.example.application.views.reviews;


import com.example.application.data.entity.Movie;
import com.example.application.data.entity.Review;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.ReviewService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.security.PermitAll;


@PageTitle("GiveReview")
@Route(value = "givereview",layout = MainLayout.class)
@PermitAll
public class GiveReviewView extends Div {

    private final AuthenticatedUser authenticatedUser;
    private final ReviewService reviewService;
    private final MovieService movieService;


    @Autowired
    public GiveReviewView(AuthenticatedUser authenticatedUser, ReviewService reviewService, MovieService movieService) {
        this.authenticatedUser = authenticatedUser;
        this.reviewService = reviewService;
        this.movieService = movieService;

        add(giveReview());
    }

    public Component giveReview(){
        VerticalLayout verticalLayout = new VerticalLayout();

        Button button = new Button("save review");
        ComboBox<Integer> rating = new ComboBox<>("rating");
        TextArea textArea= new TextArea();

        textArea.setWidth("800px");
        textArea.setHeight("200px");
        textArea.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + 600);
        });
        textArea.setLabel("Here you can give a review");
        textArea.setMaxLength(600);

        rating.setItems(1,2,3,4,5);
        rating.setPlaceholder("Select the rating from 1 to 5");

        button.addClickListener(event -> {



        });
        verticalLayout.add(textArea,rating,button);

        return verticalLayout;
    }

}
