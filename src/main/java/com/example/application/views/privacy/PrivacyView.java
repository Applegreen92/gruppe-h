package com.example.application.views.privacy;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.server.Page;

import javax.annotation.security.PermitAll;
import java.util.concurrent.TimeUnit;

@PageTitle("Privacy Settings")
    @Route(value ="PrivacySettings" , layout = MainLayout.class)
    @PermitAll
    @Uses(Icon.class)
    public class PrivacyView extends VerticalLayout {
        Grid<User> friendGrid = new Grid<>(User.class,false);
        Grid<User> watchListGrid = new Grid<>(User.class,false);
        Grid<User> watchedMoviesGrid = new Grid<>(User.class,false);

        Grid<User> recommendedMoviesGrid = new Grid<>(User.class,false);


        private final UserService userService;
        private final AuthenticatedUser authenticatedUser;

        public PrivacyView(UserService userService, AuthenticatedUser authenticatedUser) {
            this.userService = userService;
            this.authenticatedUser = authenticatedUser;
            addClassName("Privacy-View");
            configureFriendGrid();
            configureWatchListGrid();
            configureWatchedGrid();
            configureRecommendedMoviesGrid();

            add(friendGrid);
            add(watchListGrid);
            add(watchedMoviesGrid);
            add(recommendedMoviesGrid);
            updateList();
        }

        private void configureFriendGrid() {
            friendGrid.addClassNames("privacy-grid");
            friendGrid.addColumn(User::getFriendListP).setHeader("FRIENDLIST");
            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> {
                            userService.changePrivacyFriends(User,1);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();

                            });
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyFriends(User,2);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();

                        });
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            friendGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyFriends(User,3);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();

                        });
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");


                friendGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            friendGrid.setAllRowsVisible(true);
        }

        public void configureWatchListGrid() {
            watchListGrid.addClassNames("watchlist-grid");
            watchListGrid.addColumn(User::getWatchListP).setHeader("WATCHLIST");
            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User2) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> {
                            userService.changePrivacyWatchList(User2,1);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User2) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyWatchList(User2,2);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            watchListGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User2) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyWatchList(User2,3);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            watchListGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            watchListGrid.setAllRowsVisible(true);
        }

        public void configureWatchedGrid() {
            watchedMoviesGrid.addClassNames("watched-grid");
            watchedMoviesGrid.addColumn(User::getWatchedMoviesP).setHeader("WATCHED MOVIES");
            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User3) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> {
                            userService.changePrivacyWatched(User3,1);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("PUBLIC");

            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User3) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyWatched(User3,2);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.USERS));
                    })).setHeader("ONLY FRIENDS");

            watchedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User3) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyWatched(User3,3);
                            userService.updatePrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("PRIVATE");

            watchedMoviesGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            watchedMoviesGrid.setAllRowsVisible(true);
        }
        private void configureRecommendedMoviesGrid() {
            recommendedMoviesGrid.addClassNames("privacy-grid");
            recommendedMoviesGrid.addColumn(User::getRecommendedP).setHeader("RECOMMENDED MOVIES");
            recommendedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e -> {
                                    userService.changePrivacyRecommendedMovies(User, 0);
                                    userService.updateRecommendedPrivacyString(authenticatedUser.get().get());
                                    UI.getCurrent().getPage().reload();
                                });
                        button.setIcon(new Icon(VaadinIcon.LOCK));
                    })).setHeader("MY WATCHED MOVIES");

            recommendedMoviesGrid.addColumn(
                    new ComponentRenderer<>(Button::new, (button, User) -> {
                        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                                ButtonVariant.LUMO_ERROR,
                                ButtonVariant.LUMO_TERTIARY);
                        button.addClickListener(e-> {
                            userService.changePrivacyRecommendedMovies(User,1);
                            userService.updateRecommendedPrivacyString(authenticatedUser.get().get());
                            UI.getCurrent().getPage().reload();
                        });
                        button.setIcon(new Icon(VaadinIcon.GLOBE));
                    })).setHeader("FRIENDS WATCHED MOVIES");

            recommendedMoviesGrid.getColumns().forEach(col -> col.setAutoWidth(true));
            recommendedMoviesGrid.setAllRowsVisible(true);
        }

        private void updateList() {
            friendGrid.setItems(authenticatedUser.get().get());
            watchListGrid.setItems(authenticatedUser.get().get());
            watchedMoviesGrid.setItems(authenticatedUser.get().get());
            recommendedMoviesGrid.setItems(authenticatedUser.get().get());
        }
}
