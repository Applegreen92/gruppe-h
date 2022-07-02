package com.example.application.views.RecommendedView;

import com.example.application.data.entity.Movie;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;

public class MovieListViewCard extends ListItem {
    private final UserService userService;
    private final AuthenticatedUser authenticatedUser;

    public MovieListViewCard(String title, int year, String Posterurl, String genreString, Movie movie, UserService userService, AuthenticatedUser authenticatedUser) {
        this.userService = userService;
        this.authenticatedUser = authenticatedUser;
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("330px");
        div.setWidth("220px");

        Image image = new Image();
        image.setWidth("100%");
        image.setHeight("100%");
        image.setSrc(Posterurl);
        image.setAlt(title);

        div.add(image);

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(title);

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary");
        subtitle.setText(String.valueOf(year));

        Paragraph description = new Paragraph(
                genreString);
        description.addClassName("my-m");

//        Span badge = new Span();
//        badge.getElement().setAttribute("theme", "badge");
//        badge.setText("AddToWatchlist");
        Button primaryButton = new Button("AddToWatchlist");
        primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        primaryButton.addClickListener(e-> {
            userService.insertWatchList(authenticatedUser.get().get(), movie);
        });

        add(div, header, subtitle, description, primaryButton);

    }
}
