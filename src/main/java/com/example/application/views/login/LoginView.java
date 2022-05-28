package com.example.application.views.login;

import com.example.application.views.movielist.MovieListView;
import com.example.application.views.register.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.security.PermitAll;

@PageTitle("Login")
@Route("login")
@PermitAll
public class LoginView extends LoginOverlay implements BeforeLeaveListener {
    public LoginView() {

        setAction("login");

        LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());


        i18n.getHeader().setTitle("OnlyFilms");
        i18n.getHeader().setDescription("Einloggen mit Username und Passwort");
        i18n.getForm().setPassword("Passwort");
        i18n.getForm().setForgotPassword("Passwort vergessen/ Registrieren");
        i18n.setAdditionalInformation(null);
        setI18n(i18n);
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18n);
        setError(true);
        setForgotPasswordButtonVisible(true);
        setOpened(true);
        addLoginListener(loginEvent -> UI.getCurrent().navigate(MovieListView.class));
        addForgotPasswordListener(forgotPasswordEvent -> UI.getCurrent().navigate(RegisterView.class));


    }

    private boolean enteredCode() {
        return true;
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
        if (this.enteredCode()) {
            BeforeLeaveEvent.ContinueNavigationAction action = beforeLeaveEvent.postpone();
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setText("please enter verification code");
            TextField code = new TextField("enter verification code");
            confirmDialog.add(code);
            confirmDialog.addConfirmListener(confirmEvent -> action.proceed());
            confirmDialog.open();
        }


    }
}
