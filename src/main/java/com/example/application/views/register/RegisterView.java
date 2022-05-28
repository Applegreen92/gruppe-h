package com.example.application.views.register;

import com.example.application.data.service.UserRepository;
import com.example.application.security.UserDetailsServiceImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.time.LocalDate;

@PageTitle("Register")
@Route("register")
@AnonymousAllowed
public class RegisterView extends Composite {

    private final UserDetailsServiceImpl authService;

    public RegisterView(UserDetailsServiceImpl authService) {
        this.authService = authService;
    }

    @Override
    protected Component initContent() {
        TextField username =  new TextField("Username");
        PasswordField password1 =   new PasswordField("Password");
        PasswordField password2 =   new PasswordField("Confirm Password");
        TextField vorname = new TextField("Vorname");
        TextField nachname = new TextField("Nachname");
        DatePicker geburtsdatum =  new DatePicker("Geburtsdatum");
        vorname.setPrefixComponent(new Icon("vaadin", "map-marker"));
        return new VerticalLayout(
                new H2("Register"),
                username,
                password1,
                password2,
                vorname,
                nachname,
                geburtsdatum,
                new Button("Register now", event -> register (username.getValue(), password1.getValue(), password2.getValue(),
                        vorname.getValue(), nachname.getValue(), geburtsdatum.getValue() )
                )
        );


    }

    private void register(String username , String password1, String password2, String vorname, String nachname, LocalDate geburtsdatum) {
        if (username.trim().isEmpty()) {
            Notification.show("Enter a Username!");
        } else if (password1.isEmpty()) {
            Notification.show("Enter a Password");
        } else if (!password1.equals(password2)) {
            Notification.show("Passwords don't match!");
        } else {
            authService.register(username, password1, vorname, nachname, geburtsdatum);
            Notification.show("Registration successfull LOL ALS OB");
        }
    }

}