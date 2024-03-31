module com.example.thue_tnct {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.thue_tnct to javafx.fxml;
    exports com.example.thue_tnct;
    exports controller;
    opens controller to javafx.fxml;
}