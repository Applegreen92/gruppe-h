package com.example.application.views.profile;

import com.example.application.data.entity.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@Route(value = "profileView", layout = MainLayout.class)
@PageTitle("Profile Page")
@PermitAll
@Uses(Icon.class)
public class ProfileView extends VerticalLayout {
    private AuthenticatedUser authenticatedUser;

    Grid<User> grid = new Grid<>(User.class);


    public ProfileView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

}
