package com.example.application.views.RecommendedView;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;

public class MovieListViewCard extends ListItem {

    private OrderedList imageContainer;

    public MovieListViewCard(String title,int year,  String Posterurl, String genreString) {
        addClassNames("image-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l","bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

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

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("AddToWatchlist");

        add(div, header, subtitle, description, badge);

    }
}
