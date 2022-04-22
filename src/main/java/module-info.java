module com.example.gruppeh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires ormlite.core;
    requires org.jsoup;

    opens com.example.gruppeh to javafx.fxml;
    exports com.example.gruppeh;
}