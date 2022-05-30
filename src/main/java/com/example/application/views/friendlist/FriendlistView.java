package com.example.application.views.friendlist;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Friend List")
@Route(value = "Friend List", layout = MainLayout.class)
@PermitAll

public class FriendlistView extends Div {
}
