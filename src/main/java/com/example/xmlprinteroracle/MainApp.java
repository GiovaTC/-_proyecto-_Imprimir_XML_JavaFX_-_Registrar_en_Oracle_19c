package com.example.xmlprinteroracle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

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

        HBox controls = new HBox(10, btnOpen,btnPrint,btnSaveDb);
        controls.setPadding(new Insets(10));

        statusLabel = new Label("Listo");
        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(xmlArea);
        root.setBottom(statusLabel);
        BorderPane.setMargin(xmlArea, new Insets(10));

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openXml(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            try {
                String content = Files.readString(Path.of(file.toURI()));
                xmlArea.setText(content);
                currentFile = file;
                statusLabel.setText("Archivo cargado: " +  file.getName());
            } catch (IOException ex) {
                statusLabel.setText("Error al leer archivo: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void saveToDatabase() {
        String xml = xmlArea.getText();
        if (xml == null || xml.isEmpty()) {
            statusLabel.setText("No hay xml para guardar.");
            return;
        }

        statusLabel.setText("Guardando en BD...");
        try {
            DBHelper db = new DBHelper();
            db.insertarXml(currentFile != null ? currentFile.getName() : "sin_nombre.xml", "usuario_app", xml);
            statusLabel.setText("guardado correctamente en la BD.");
        } catch (Exception ex) {
            statusLabel.setText("Error al guardar en BD: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
