package com.example.application.views.crawler;


import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.router.PageTitle;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@PageTitle("Crawler")
@Route(value = "crawler", layout = MainLayout.class)

//TODO switch to RolesAllowed("Admin") when done
@PermitAll

public class CrawlerView extends Div{
    private TextField startDate = new TextField("Start year");
    private TextField endDate = new TextField("End year");
    private TextField genre = new TextField("Genre");

    public CrawlerView() {
        add(createFormLayout());
        add(createExecButton());

    }

    //TODO connect via Actiontrigger
    public Button createExecButton(){
        Button primary = new Button("Get Movies");
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
