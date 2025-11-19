package com.example.xmlprinteroracle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DBHelper {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void insertarXml(String s, String usuarioApp, String xml) {
    }
}
