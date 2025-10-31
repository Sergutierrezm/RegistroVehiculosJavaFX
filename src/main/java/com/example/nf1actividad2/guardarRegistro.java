package com.example.nf1actividad2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

import java.util.HashMap;
import java.util.Map;

public class guardarRegistro {

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblMarca;

    @FXML
    private Label lblAnio;

    @FXML
    private Label lblValor;

    @FXML
    private CheckBox chkConfirmar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnSalir;

    // Método para recibir los datos desde usuarioRegistro
    public void setDatos(String matricula, String marca, int anio, String valor) {
        lblMatricula.setText(matricula);
        lblMarca.setText(marca);
        lblAnio.setText(String.valueOf(anio));
        lblValor.setText(valor);
    }

    @FXML
    private void guardarRegistro(ActionEvent event) {
        if (!chkConfirmar.isSelected()) {
            // aviso al usuario...
            return;
        }

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("matricula", lblMatricula.getText());
            params.put("marca", lblMarca.getText());
            params.put("anio", lblAnio.getText());
            params.put("valor", lblValor.getText());

            // Recurso jrxml en resources/reportes/
            String jrxml = "/com/example/nf1actividad2/reportes/informeVehiculo.jrxml";
            String outputPdf = "informeVehiculo.pdf"; // se crea en working dir (o pon ruta absoluta)

            System.out.println("=== Parámetros ===");
            params.forEach((k, v) -> System.out.println(k + " => " + v));
            ReporteUtil.generarPdfDesdeJrxml(jrxml, outputPdf, params);

            // informar al usuario (Alert)
            Alert ok = new Alert(Alert.AlertType.INFORMATION, "PDF generado: " + outputPdf);
            ok.showAndWait();
        } catch (JRException e) {
            e.printStackTrace();
            Alert err = new Alert(Alert.AlertType.ERROR, "Error al generar PDF: " + e.getMessage());
            err.showAndWait();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}