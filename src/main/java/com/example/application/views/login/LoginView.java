package com.example.application.views.login;

import com.example.application.views.MainLayout;
import com.example.application.views.movielist.MovieListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.NavigationEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.security.PermitAll;

@PageTitle("Login")
@Route("login")
@PermitAll
public class LoginView extends LoginOverlay {
    public LoginView() {
        setAction("login");

        LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());

        i18n.getHeader().setTitle("OnlyFilms");
        i18n.getHeader().setDescription("Einloggen mit Username und Passwort");
        i18n.getForm().setPassword("Passwort");
        i18n.getForm().setForgotPassword("Passwort vergessen/ Registrieren");
        addLoginListener(loginEvent -> UI.getCurrent().navigate(MovieListView.class));
        addForgotPasswordListener(forgotPasswordEvent -> UI.getCurrent().navigate(RegisterView.class));
        i18n.setAdditionalInformation(null);

        setI18n(i18n);
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18n);
        setForgotPasswordButtonVisible(true);
        setOpened(true);







    }

}
