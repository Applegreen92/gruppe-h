package com.example.application.views.search;

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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.Optional;

@Route(value = "searchUser", layout = MainLayout.class)
@PageTitle("Search User")
@PermitAll
@Uses(Icon.class)
public class SearchView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);
    TextField filterText = new TextField();

    private final UserService userService;
    private AuthenticatedUser authenticatedUser;

    public SearchView(UserService userService, AuthenticatedUser authenticatedUser) {
        this.userService = userService;
        this.authenticatedUser = authenticatedUser;
        addClassName("Search-View");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
        updateList();
    }


    private void configureGrid() {
        grid.addClassNames("user-grid");
        grid.setSizeFull();
        grid.setColumns("username", "name");
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, UI) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> switchTo());
                    button.setIcon(new Icon(VaadinIcon.USER_CARD));
                })).setHeader("View profile");


        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, User) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e-> userService.addFriend(getAuthUser(),User));
                    button.setIcon(new Icon(VaadinIcon.PLUS));
                })).setHeader("Add friend");

        // grid.addColumn(user -> user.getStatus().getName()).setHeader("Status"); // für den Status später
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
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


    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Type in a name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());


        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(userService.findAllUsers(filterText.getValue()));
    }
}


