module com.view {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires ormlite.core;
    requires org.jsoup;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires htmlunit;


    opens com.view to javafx.fxml;
    exports com.view;
    exports com.controller;
    exports com.model;
    exports com.testPackage;
    opens com.testPackage to javafx.fxml;

    opens com.controller to javafx.fxml;

}