package com.example.application.views.profile;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;



@Route(value = "profileView", layout = MainLayout.class)
@PageTitle("Profile Page")
@PermitAll
@Uses(Icon.class)
public class ProfileView extends VerticalLayout implements HasUrlParameter<String> {

    private String userID;

    private final AuthenticatedUser authenticatedUser;
    private User user;
    private UserService userService;

    Grid<User> grid = new Grid<>(User.class);

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");

    private TextField userNameField = new TextField("User name");

    private Button watchedMovies = new Button("See watched Movies");
    private Button watchList = new Button("See Watchlist");
    private Button seeFriends = new Button("See Friends");
    private Button privacy = new Button("Privacy Settings", new Icon(VaadinIcon.COG));

    String siteRef = "http://localhost:8080/watchedMovies/";

    private Binder<User> binder = new Binder<>(User.class);


    //Warning USERNAME has to be UNIQUE
    public ProfileView(UserService userService, AuthenticatedUser authenticatedUser) {
        this.userService = userService;
        this.authenticatedUser = authenticatedUser;
        user = authenticatedUser.get().get();


        addClassName("profile-view");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        binder.bindInstanceFields(this);
        clearForm();
        if (this.user == null) {
            firstName.setValue(authenticatedUser.get().get().getFirstname());
            lastName.setValue(authenticatedUser.get().get().getLastname());
            email.setValue(authenticatedUser.get().get().getEmail());

            userNameField.setValue(authenticatedUser.get().get().getUsername());
        } else{
            firstName.setValue(user.getFirstname());
            lastName.setValue(user.getLastname());
            email.setValue(user.getEmail());
            userNameField.setValue(user.getUsername());
        }
    }


    private void clearForm() {
        binder.setBean(new User());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }


    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, email, userNameField);
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

        String siteComp = siteRef + user.getUsername();
        watchedMovies.addClickListener(e -> UI.getCurrent().navigate(siteComp));
        //TODO add watchlist and seeFriends URl
        watchList.addClickListener((e -> UI.getCurrent().navigate(Watchlist.class)));
        seeFriends.addClickListener(e -> UI.getCurrent().navigate(FriendlistView.class));
        privacy.addClickListener(e -> UI.getCurrent().navigate(PrivacyView.class));

        buttonLayout.add(watchedMovies);
        buttonLayout.add(watchList);
        buttonLayout.add(seeFriends);
        buttonLayout.add(privacy);
        return buttonLayout;
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String userName) {

        if (userName == null) {
            user = authenticatedUser.get().get();
        } else {
            user = userService.findByUsername(userName);
            add(createButtonLayout());
            firstName.setValue(user.getFirstname());
            lastName.setValue(user.getLastname());
            email.setValue(user.getEmail());
            userNameField.setValue(user.getUsername());

            if (userName == null) {
            } else {
                this.user = userService.findByUsername(userName);
                firstName.setValue(user.getFirstname());
                lastName.setValue(user.getLastname());
                email.setValue(user.getEmail());
                updateTitle(user);

                if (userName == null) {
                } else {
                    this.user = userService.findByUsername(userName);
                    firstName.setValue(user.getFirstname());
                    lastName.setValue(user.getLastname());
                    email.setValue(user.getEmail());
                    updateTitle(user);
                    //System.out.println((userService.isFriend(authenticatedUser.get().get(), user)));
                    for (User user : user.getFriends()) {
                        System.out.println(user.getUsername());
                    }
                    for (User user : authenticatedUser.get().get().getFriends()) {
                        System.out.println(user.getUsername());
                    }
                    if (user.getWatchedMoviesPrivacy() == 3) {
                        watchList.setVisible(false);
                    }
                    if (user.getWatchedMoviesPrivacy() == 2 && userService.isFriend(authenticatedUser.get().get(), user) == false) {
                        System.out.println("Not friends");
                        watchList.setVisible(false);
                    } else {
                        watchList.setVisible(true);
                    }
                }

            }
        }
    }
}








