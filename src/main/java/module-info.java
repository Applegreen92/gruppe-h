module com.view {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires ormlite.core;
    requires org.jsoup;

    opens com.view to javafx.fxml;
    exports com.view;
    exports com.controller;
}