package com.example.application.views.search;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Search")
@Route(value = "search", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class SearchView extends Composite {

    @Override
    protected Component initContent() {
        TextField searchUser = new TextField("Search User:");
        searchUser.setPrefixComponent(new Icon("vaadin", "search"));

        HorizontalLayout helper = new HorizontalLayout(
                searchUser
        );

        return helper;
    }



}