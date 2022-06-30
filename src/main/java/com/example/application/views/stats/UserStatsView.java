package com.example.application.views.stats;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

@PageTitle("User Stats")
@Route(value ="userStats" , layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)


public class UserStatsView extends VerticalLayout implements HasUrlParameter<String> {
    private User user;
    Grid<User> grid = new Grid<>(User.class);


    private TextField watchtime = new TextField("Total Watchtime");
    private TextField favActor = new TextField("Favourite Actor");
    private TextField favGenre = new TextField("Favourite Genre");
    private TextField favMovie = new TextField("Favourite Movie");

    private UserService userService;




    public UserStatsView(UserService userService){
        this.userService = userService;

    }

    private Component createTitle() {
        return new H3("Stats for: " + user.getUsername());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(watchtime, favGenre, favActor, favMovie);
        return formLayout;
    }

    private void fillTextFields(){

    }
    private Component createButtonLayout() {
       return null;
    }



    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String name) {
        if(name== null || name.isEmpty()){



        }
        else {
            this.user = userService.findByUsername(name);

            add(createTitle());
            add(createFormLayout());
            add(createButtonLayout());
            fillTextFields();

        }
    }
}
