package com.example.application.views.login;

import com.example.application.security.AuthenticatedUser;
import com.example.application.security.email.EmailSenderService;
import com.example.application.security.twofactor.ConfirmationToken;
import com.example.application.security.twofactor.ConfirmationTokenService;
import com.example.application.views.movielist.MovieListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;
import java.util.UUID;

@Route("ConfirmCode")
@PageTitle("Please confrim your code")
@PermitAll
public class loginConfirmCode extends Composite {

    private final AuthenticatedUser authenticatedUser;

    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private final EmailSenderService emailService;


    private boolean codeDummy = false;

    public loginConfirmCode(AuthenticatedUser authenticatedUser, ConfirmationTokenService confirmationTokenService,  EmailSenderService emailService) {
        this.authenticatedUser = authenticatedUser;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
        codeSend();
    }

    public void sendMail(String token) {
        emailService.sendEmail(this.authenticatedUser.get().get().getEmail(),"OnlyFilms confirmation code", token );
    }

    private void codeSend() {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15)
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        sendMail(token);
    }

    @Override
    protected Component initContent() {
        TextField code = new TextField("Enter verfication code");
        Button confirm = new Button("Confirm", buttonClickEvent -> confirm(code.getValue()));
        return new HorizontalLayout(
                code,
                confirm
        );
    }

    private void confirm(String value) {
        if (confirmationTokenService.checkConfirmationToken(value)) {
            Notification.show("wrong confirmation token");
        }

         if (confirmationTokenService.checkConfirmationToken(value) == false){
             Notification.show("Correct Code!");
             UI.getCurrent().navigate(MovieListView.class);
        }
    }





    private boolean notEnteredCode() {
        return codeDummy;
    }



}
