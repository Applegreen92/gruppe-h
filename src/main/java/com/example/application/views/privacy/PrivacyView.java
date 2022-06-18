package com.example.application.views.privacy;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
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

    @PageTitle("Privacy Settings")
    @Route(value ="PrivacySettings" , layout = MainLayout.class)
    @PermitAll
    @Uses(Icon.class)
    public class PrivacyView extends VerticalLayout {
        Grid<User> friendGrid = new Grid<>(User.class,false);
        Grid<User> watchListGrid = new Grid<>(User.class,false);
        Grid<User> watchedMoviesGrid = new Grid<>(User.class,false);

        Grid<User> recommandedMoviesGrid = new Grid<>(User.class,false);


        private final UserService userService;
        private final AuthenticatedUser authenticatedUser;

        public PrivacyView(UserService userService, AuthenticatedUser authenticatedUser) {
            this.userService = userService;
            this.authenticatedUser = authenticatedUser;
            addClassName("Privacy-View");
            configureFriendGrid();
            configureWatchListGrid();
            configureWatchedGrid();
            configureRecommandedMoviesGrid();

            add(friendGrid);
            add(watchListGrid);
            add(watchedMoviesGrid);
            add(recommandedMoviesGrid);
            updateList();
        }

        private void configureFriendGrid() {
            friendGrid.addClassNames("privacy-grid");
            friendGrid.setColumns("friendListPrivacy");
            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> userService.changePrivacyFriends(User,1));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyFriends(User,2));
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyFriends(User,3));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            friendGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            friendGrid.setAllRowsVisible(true);
        }

        public void configureWatchListGrid() {
            watchListGrid.addClassNames("watchlist-grid");
            watchListGrid.setColumns("watchListPrivacy");
            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User2) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> userService.changePrivacyWatchList(User2,1));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User2) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyWatchList(User2,2));
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User2) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyWatchList(User2,3));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            watchListGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            watchListGrid.setAllRowsVisible(true);
        }

        public void configureWatchedGrid() {
            watchedMoviesGrid.addClassNames("watched-grid");
            watchedMoviesGrid.setColumns("watchedMoviesPrivacy");
            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User3) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> userService.changePrivacyWatched(User3,1));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User3) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyWatched(User3,2));
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User3) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyWatched(User3,3));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            watchedMoviesGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            watchedMoviesGrid.setAllRowsVisible(true);
        }
        private void configureRecommandedMoviesGrid() {
            recommandedMoviesGrid.addClassNames("privacy-grid");
            recommandedMoviesGrid.setColumns("recommandedMoviesPrivacy");
            recommandedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> userService.changePrivacyRecommandedMovies(User,0));
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("My Watched Movies");

            recommandedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> userService.changePrivacyRecommandedMovies(User,1));
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("Friends Watches Movies");

            recommandedMoviesGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            recommandedMoviesGrid.setAllRowsVisible(true);
        }

        private void updateList() {
            friendGrid.setItems(authenticatedUser.get().get());
            watchListGrid.setItems(authenticatedUser.get().get());
            watchedMoviesGrid.setItems(authenticatedUser.get().get());
            recommandedMoviesGrid.setItems(authenticatedUser.get().get());
        }
}
