package com.example.application.views.crawler;


import com.example.application.views.MainLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.router.PageTitle;

import javax.annotation.security.RolesAllowed;

@PageTitle("Crawler")
@Route(value = "crawler", layout = MainLayout.class)
@RolesAllowed("ADMIN")

public class CrawlerView extends Div{

    public void FormLayoutBasic() {
        TextField startDate = new TextField("Start Date");
        TextField endDate = new TextField("End Date");
        TextField genre = new TextField("Genre");


        FormLayout crawlerView = new FormLayout();
        crawlerView.add(startDate, endDate, genre);

        crawlerView.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2)
        );
        // Stretch the username field over 2 columns
        crawlerView.setColspan(genre, 2);

        add(crawlerView);
    }


}
