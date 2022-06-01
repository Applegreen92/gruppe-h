package com.example.application.views.privacy;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.example.application.views.movielist.MovieListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.Optional;

    @PageTitle("Privacy Settings")
    @Route(value ="PrivacySettings" , layout = MainLayout.class)
    @PermitAll
    @Uses(Icon.class)
    public class PrivacyView extends VerticalLayout {
        Grid<User> friendGrid = new Grid<>(User.class,false);
        Grid<User> watchListGrid = new Grid<>(User.class,false);
        Grid<User> watchedMoviesGrid = new Grid<>(User.class,false);


        private final UserService userService;
        private final AuthenticatedUser authenticatedUser;

        public PrivacyView(UserService userService, AuthenticatedUser authenticatedUser) {
            this.userService = userService;
            this.authenticatedUser = authenticatedUser;
            addClassName("Privacy-View");
            configureFriendGrid();
            configureWatchListGrid();
            configureWatchedGrid();

            add(friendGrid);
            add(watchListGrid);
            add(watchedMoviesGrid);
            updateList();
        }



        private void configureFriendGrid() {
            friendGrid.addClassNames("privacy-grid");
            friendGrid.setColumns("friendListPrivacy");
            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, UI) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> System.out.println("PU"));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> System.out.println("O"));
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> System.out.println("PR"));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            // grid.addColumn(user -> user.getStatus().getName()).setHeader("Status"); // für den Status später
            friendGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            friendGrid.setAllRowsVisible(true);
        }

        public void configureWatchListGrid() {
            watchListGrid.addClassNames("watchlist-grid");
            watchListGrid.setColumns("watchListPrivacy");
            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, UI) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> System.out.println("PU"));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> System.out.println("O"));
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> System.out.println("PR"));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            // grid.addColumn(user -> user.getStatus().getName()).setHeader("Status"); // für den Status später
            watchListGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            watchListGrid.setAllRowsVisible(true);
        }

        public void configureWatchedGrid() {
            watchedMoviesGrid.addClassNames("watched-grid");
            watchedMoviesGrid.setColumns("watchedMoviesPrivacy");
            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, UI) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> System.out.println("PU"));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> System.out.println("O"));
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> System.out.println("PR"));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            // grid.addColumn(user -> user.getStatus().getName()).setHeader("Status"); // für den Status später
            watchedMoviesGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            watchedMoviesGrid.setAllRowsVisible(true);
        }


        private void switchTo() {
            UI.getCurrent().navigate(MovieListView.class);
        }

        private User getAuthUser() {
            Optional<User> maybeUser = authenticatedUser.get();
            if (maybeUser.isPresent()) {
                User user = maybeUser.get();
                return user;
            }
            return null;
        }



        private void updateList() {
            friendGrid.setItems(authenticatedUser.get().get());
            watchListGrid.setItems(authenticatedUser.get().get());
            watchedMoviesGrid.setItems(authenticatedUser.get().get());
        }
}
