package com.example.application.views.bugReport;

import com.example.application.data.entity.BugReport;
import com.example.application.data.service.BugReportService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@PageTitle("Bug Report")
@Route(value = "BugReport", layout = MainLayout.class)

@PermitAll

public class BugReportView extends Div{

    private final BugReportService bugReportService;
    private TextField Topic = new TextField("Topic");
    private TextArea Description = new TextArea("Description");

//    private Upload upload = new Upload();
//
//    private final FileBuffer buffer = new FileBuffer ();
//    private String path;



    public BugReportView(BugReportService bugReportService) {
        this.bugReportService = bugReportService;
        add(VerticalLayout());
    }

    public Button createExecButton(){
        Button primary = new Button("Report Bug",event -> {
            BugReport bugReport = new BugReport(Topic.getValue(),Description.getValue());
            if(bugReportService.insertBug(bugReport)){
                Notification notification = Notification.show("Bug was Created!");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else {
                Notification notification = Notification.show("Failed to create Bug!");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        primary.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        primary.isDisableOnClick();
        return primary;
    }

    public VerticalLayout VerticalLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.add(Topic);
        layout.add(Description);
        Topic.setMaxLength(250);
        Topic.setValueChangeMode(ValueChangeMode.EAGER);
        Topic.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + 250);
        });
        Description.setMaxLength(999);
        Description.setValueChangeMode(ValueChangeMode.EAGER);
        Description.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + 999);
        });
        Description.setWidthFull();
        layout.setPadding(true);
        layout.add(createExecButton());
        return layout;
    }


}
