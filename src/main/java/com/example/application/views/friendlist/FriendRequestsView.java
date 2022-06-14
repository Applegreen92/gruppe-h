package com.example.application.views.friendlist;


import com.example.application.data.entity.User;
import com.example.application.views.chat.ChatView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@PageTitle("Friend Requests")
@Route(value = "Friendrequests")
@PermitAll
public class FriendRequestsView extends VerticalLayout {



  /*  public void FriendlistRequestsView() {


        setHeightFull();
        setWidth("500px");

        Grid<User> grid = new Grid<>(User.class, false);
        grid.addColumn(User::getUsername).setHeader("User")
                .setAutoWidth(true).setFlexGrow(0).setComparator(User::getFirstname);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, User) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> UI.getCurrent().navigate(ChatView.class));
                    button.setIcon(new Icon(VaadinIcon.ARROW_RIGHT));
                })).setHeader("Annehmen/Ablehnen");
        expand(grid);


        add(grid);


    }

*/

}
