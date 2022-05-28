package com.example.application.views.crawler;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import com.example.application.data.generator.Crawler;
import com.example.application.data.service.MoviePersonPartLinkService;
import com.example.application.views.MainLayout;
import javax.annotation.security.PermitAll;
import java.io.IOException;


@PageTitle("Crawler")
@Route(value = "crawler", layout = MainLayout.class)

//TODO switch to RolesAllowed("Admin") when done
@PermitAll

public class CrawlerView extends Div{
    private final Crawler crawler;

    private TextField startDate = new TextField("Start year");
    private TextField endDate = new TextField("End year");
    private TextField genre = new TextField("Genre");

    public CrawlerView(MoviePersonPartLinkService moviePersonPartLinkService) {

        this.crawler = new Crawler(moviePersonPartLinkService);
        add(createFormLayout());
        add(horizontalLayout());


//        if(Integer.parseInt(String.valueOf(startDate)) > Integer.parseInt(String.valueOf(endDate))){
//            Notification.show("Start date must be greater than end date!");
//        }
    }

    public Button createExecButton(){
        Button primary = new Button("Get Movies",event -> {
            try {
                crawler.getMoviesByGenre(genre.getValue(),0,Integer.parseInt(startDate.getValue()),Integer.parseInt(endDate.getValue()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        primary.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        primary.isDisableOnClick();
        return primary;
    }

    public Component createFormLayout(){
        FormLayout crawlerView = new FormLayout();
        crawlerView.add(startDate, endDate, genre);
        return crawlerView;
    }

    public Component horizontalLayout(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.add(createExecButton());
        return layout;
    }



}
