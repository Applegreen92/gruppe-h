package com.example.application.views.bugReport;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@PageTitle("Bug Report")
@Route(value = "BugReport", layout = MainLayout.class)

@PermitAll

public class BugReportView extends Div{

    private TextField Topic = new TextField("Topic");
    private TextArea Description = new TextArea("Description");

    public BugReportView() {
        add(createFormLayout());
        add(VerticalLayout());

    }

    public Button createExecButton(){
        Button primary = new Button("Report Bug",event -> {
        });
        primary.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        primary.isDisableOnClick();
        return primary;
    }

    public Component createFormLayout(){
        FormLayout bugReportView = new FormLayout();
        //bugReportView.add(Topic);
        //bugReportView.add(Description);
        return bugReportView;
    }

    public VerticalLayout VerticalLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.add(Topic);
        layout.add(Description);
        textArea.setWidthFull();
        layout.setPadding(true);
        layout.add(createExecButton());
        return layout;
    }
    TextArea textArea = new TextArea();

}
