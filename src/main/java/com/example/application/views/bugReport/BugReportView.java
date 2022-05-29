package com.example.application.views.bugReport;

import com.example.application.data.entity.BugReport;
import com.example.application.data.service.BugReportService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.compress.utils.IOUtils;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


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
            bugReportService.insertBug(bugReport);
        });
        primary.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        primary.isDisableOnClick();
        return primary;
    }

    public VerticalLayout VerticalLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.add(Topic);
        layout.add(Description);
        Description.setWidthFull();
        layout.setPadding(true);
        layout.add(createExecButton());
        return layout;
    }

}
