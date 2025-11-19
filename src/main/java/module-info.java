module com.example.xmlprinteroracle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.xmlprinteroracle to javafx.fxml;
    exports com.example.xmlprinteroracle;
}