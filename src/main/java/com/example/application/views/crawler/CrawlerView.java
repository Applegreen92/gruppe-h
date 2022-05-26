package com.example.application.views.crawler;


import com.example.application.data.service.MoviePersonPartLinkService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.example.application.data.generator.Crawler;
import com.vaadin.flow.router.PageTitle;

import javax.annotation.security.PermitAll;
import java.io.IOException;

@PageTitle("Crawler")
@Route(value = "crawler", layout = MainLayout.class)

//TODO switch to RolesAllowed("Admin") when done
@PermitAll

public class CrawlerView extends Div{
    private final Crawler crawler;
    private final MoviePersonPartLinkService moviePersonPartLinkService;
    private TextField startDate = new TextField("Start year");
    private TextField endDate = new TextField("End year");
    private TextField genre = new TextField("Genre");

    public CrawlerView(MoviePersonPartLinkService moviePersonPartLinkService) {

        this.moviePersonPartLinkService = moviePersonPartLinkService;
        this.crawler = new Crawler(moviePersonPartLinkService);
        add(createFormLayout());
        add(createExecButton());

    }

    //TODO connect via Actiontrigger
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


}
