package com.example.application.views.friendlist;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import javax.annotation.security.PermitAll;

@PageTitle("Friend List")
@Route(value = "FriendList", layout = MainLayout.class)
@PermitAll

public class FriendlistView extends VerticalLayout {


    public FriendlistView() {
        setHeightFull();
        setWidth("600px");
    var last = createButton("add Friend");

    Avatar avatarName = new Avatar("TEST");
        avatarName.addThemeVariants(AvatarVariant.LUMO_XLARGE);
    var header = new HorizontalLayout(avatarName,createButton("Name"),last);
    header.setWidthFull();
    last.getStyle().set("margin-left","auto");
    header.setAlignItems(Alignment.BASELINE);
    header.setSpacing(true);
    header.setJustifyContentMode(JustifyContentMode.BETWEEN);
    last.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

    var friends = new HorizontalLayout();
    friends.setWidthFull();
    friends.setHeight("400px");


    var bottom1 = createButton("Profile");
    var bottom2 = createButton("Private Settings");
    var bottombar = new HorizontalLayout(bottom1,bottom2);
    bottombar.setWidthFull();
        bottombar.setJustifyContentMode(JustifyContentMode.CENTER);
        bottombar.setFlexGrow(1,bottom1);
        bottombar.setFlexGrow(1,bottom2);
        bottom1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        bottom2.addThemeVariants(ButtonVariant.LUMO_ERROR);
    add(header,friends,bottombar);


}
public Button createButton(String texts) {
    var button = new Button(texts);
    button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    return button;


    }
}
