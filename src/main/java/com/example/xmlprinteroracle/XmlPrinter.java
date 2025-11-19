package com.example.xmlprinteroracle;

import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.transform.Scale;

public class XmlPrinter {
    /**
     * Imprimer el contenido del TextArea usando PrintweJob de Javafx.
     */
    public static void printNode(TextArea textArea) {
        Printer printer = Printer.getDefaultPrinter();
        if (printer == null) {
            System.err.println("no hay impresora por defecto disponible.");
            return;
        }

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job == null) {
            System.err.println("No se pudo crear PrinterJob.");
            return;
        }

        boolean proceed = job.showPrintDialog(textArea.getScene().getWindow());
        if (!proceed) {
            return;
        }

        PageLayout pl = job.getJobSettings().getPageLayout();

        // Escalado simple para ajustar el contenido si es necesario
        double scaleX = pl.getPrintableWidth() /  textArea.getWidth();
        double scaleY = pl.getPrintableHeight() /  textArea.getHeight();
        double scale = Math.min(scaleX, scaleY);
        if (scale <= 0) scale = 1.0;

        Scale st = new Scale(scale, scale);
        textArea.getTransforms().add(st);

        boolean success = job.printPage(textArea);

        textArea.getTransforms().remove(st);

        if (success) {
            job.endJob();
            System.out.println("Impresion enviada correctamente.");
        } else {
            System.err.println("Fallo la impresion.");
        }
    }
}
