package com.example.application.views.profile;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.entity.User;
import com.example.application.data.service.SamplePersonService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.example.application.views.friendlist.FriendlistView;
import com.example.application.views.privacy.PrivacyView;
import com.example.application.views.watchedMoviesList.WatchedMoviesView;
import com.example.application.views.watchlist.Watchlist;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
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

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");

    private Button watchedMovies = new Button("See watched Movies");
    private Button watchList = new Button("See Watchlist");
    private Button seeFriends = new Button("See Friends");
    private Button privacy = new Button("Privacy Settings");





    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);

    public ProfileView(SamplePersonService personService, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        addClassName("profile-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

       firstName.setValue(authenticatedUser.get().get().getFirstname());
       lastName.setValue(authenticatedUser.get().get().getLastname());
       email.setValue(authenticatedUser.get().get().getEmail());

    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Personal information of " + authenticatedUser.get().get().getFirstname());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, email);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        watchedMovies.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        watchList.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        seeFriends.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        privacy.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        //TODO switch MovieListView to WatchList and switch to personalized page
        watchedMovies.addClickListener(e -> UI.getCurrent().navigate(WatchedMoviesView.class));
        watchList.addClickListener((e -> UI.getCurrent().navigate(Watchlist.class)));
        seeFriends.addClickListener(e -> UI.getCurrent().navigate(FriendlistView.class));
        privacy.addClickListener(e-> UI.getCurrent().navigate(PrivacyView.class));

        buttonLayout.add(watchedMovies);
        buttonLayout.add(watchList);
        buttonLayout.add(seeFriends);
        buttonLayout.add(privacy);
        return buttonLayout;
    }

}




