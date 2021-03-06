package com.example.application.views.friendlist;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.security.AuthenticatedUser;
import com.example.application.views.MainLayout;
import com.example.application.views.chat.ChatView;
import com.example.application.views.privacy.PrivacyView;
import com.example.application.views.profile.ProfileView;
import com.example.application.views.search.SearchView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import javax.annotation.security.PermitAll;

@PageTitle("Friend List")
@Route(value = "FriendList", layout = MainLayout.class)
@PermitAll

public class FriendlistView extends VerticalLayout {

    Grid<User> grid = new Grid<>(User.class);
    private final AuthenticatedUser authenticatedUser;
    private final UserService userService;

    public FriendlistView(AuthenticatedUser authenticatedUser, UserService userService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        setHeightFull();
        setWidth("600px");

        // header bar
        Button last= new Button("add Friend",new Icon(VaadinIcon.PLUS));
        Button requests = new Button("Friend Requests");
        last.addThemeVariants(ButtonVariant.LUMO_LARGE);
        last.addClickListener(e -> UI.getCurrent().navigate(SearchView.class));
        requests.addClickListener(e -> UI.getCurrent().navigate(FriendRequestsView.class));

        Avatar avatarName = new Avatar(authenticatedUser.get().get().getName());
        avatarName.addThemeVariants(AvatarVariant.LUMO_XLARGE);
        Text userField = new Text(authenticatedUser.get().get().getUsername());
        var header = new HorizontalLayout(avatarName,userField,requests,last);
        header.setWidthFull();
        last.getStyle().set("margin-left","auto");
        header.setAlignItems(Alignment.BASELINE);
        header.setSpacing(true);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        last.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        grid.setColumns("username");

        // Main friend list box

        grid.addColumn(createEmployeeRenderer()).setHeader("Avatar")
                .setAutoWidth(true).setFlexGrow(0).setComparator(User::getFirstname);


        //grid.addColumn(User::getFriends).setHeader("Friend");

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, User) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> UI.getCurrent().navigate(ChatView.class));
                    button.setIcon(new Icon(VaadinIcon.ARROW_RIGHT));
                })).setHeader("Chatfenster");
        expand(grid);

        //add(grid);
        //configureGrid();
        // bottom bar
        var bottom1 = createButton("Profil");
        Button bottom2 = new Button   ("Privacy Settings",new Icon(VaadinIcon.COG));
        bottom1.addClickListener(e -> UI.getCurrent().navigate(ProfileView.class));
        bottom2.addClickListener(e-> UI.getCurrent().navigate(PrivacyView.class));
        var bottombar = new HorizontalLayout(bottom1,bottom2);
        bottombar.setWidthFull();
        bottombar.setJustifyContentMode(JustifyContentMode.CENTER);
        bottombar.setFlexGrow(1,bottom1);
        bottombar.setFlexGrow(1,bottom2);
        bottom1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        bottom2.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(header,grid,bottombar);
        updateList();

    }
    public Button createButton(String text) {
        var button = new Button(text);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return button;


    }

    private static Renderer<User> createEmployeeRenderer() {
        return LitRenderer.<User>of(
                        "<vaadin-horizontal-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "<vaadin-avatar img=\"${item.pictureUrl}\" name=\"${item.fullName}\" alt=\"User avatar\"></vaadin-avatar>"
                                + "  <vaadin-vertical-layout style=\"line-height: var(--lumo-line-height-m);\">"
                                + "    <span> ${item.fullName} </span>"
                                + "    <span style=\"font-size: var(--lumo-font-size-s); color: var(--lumo-secondary-text-color);\">"
                                + "      ${item.email}" + "    </span>"
                                + "  </vaadin-vertical-layout>"
                                + "</vaadin-horizontal-layout>")

                .withProperty("pictureUrl", User::getProfilePictureUrl)
                .withProperty("FirstName", User::getFirstname)
                .withProperty("LastName", User::getLastname);
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.addColumn(User::getFriends).setHeader("Friend");
    }

    private void updateList() {
        grid.setItems(userService.findByUsername(authenticatedUser.get().get().getUsername()).getFriends());
    }

}