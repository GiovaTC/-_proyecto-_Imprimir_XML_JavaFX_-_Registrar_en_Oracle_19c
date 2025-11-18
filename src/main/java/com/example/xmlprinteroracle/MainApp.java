package com.example.xmlprinteroracle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApp extends Application {

    private TextArea xmlArea;
    private Label statusLabel;
    private File currentFile;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Imprimir xml y guardar en oracle 19c");

        xmlArea = new TextArea();
        xmlArea.setWrapText(false);
        xmlArea.setPrefRowCount(40);
        xmlArea.setPrefColumnCount(120);

        Button btnOpen = new Button("abrir xml");
        Button btnPrint = new Button("inprimir");
        Button btnSaveDb = new Button("guardar en BD ( oracle )");

        btnOpen.setOnAction(e -> openXml(primaryStage));
        btnPrint.setOnAction(e -> XmlPrinter.printNode(xmlArea));
        btnSaveDb.setOnAction(e -> saveToDatabase());

    }

    private void openXml(Stage primaryStage) {}

    private void saveToDatabase() {}

}
