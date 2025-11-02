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

// Clase que gestiona la ventana donde muestra la informacion del vehiculo registrado
//Permite generar un informe en pdf con JasperReports

public class guardarRegistro {

    // Referencia a los elementos difinidos en el archivo FXML
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

    // Método para recibir los datos desde la ventana usuario registro

    public void setDatos(String matricula, String marca, int anio, String valor) {
        lblMatricula.setText(matricula);
        lblMarca.setText(marca);
        lblAnio.setText(String.valueOf(anio));
        lblValor.setText(valor);
    }
    //Metodo que se ejecuta cuando el usuario pulsa el boton "guardar"
    @FXML
    private void guardarRegistro(ActionEvent event) {
        if (!chkConfirmar.isSelected()) { //verifica si el usuario ha marcado la casilla de confirmacion

            return;
        }

        try {   //crea un mapa de parametros con los datos del vehiculo
            Map<String, Object> params = new HashMap<>();
            params.put("matricula", lblMatricula.getText());
            params.put("marca", lblMarca.getText());
            params.put("anio", lblAnio.getText());
            params.put("valor", lblValor.getText());

            // ruta del archivo jrxml dentro de la carpeta resource
            String jrxml = "/com/example/nf1actividad2/reportes/informeVehiculo.jrxml";
            //Nombre del pdf que se generará
            String outputPdf = "informeVehiculo.pdf"; //

            System.out.println("=== Parámetros ===");
            params.forEach((k, v) -> System.out.println(k + " => " + v));
            //Genera el pdf a partir de la plantilla jrxml y los datos del mapa
            ReporteUtil.generarPdfDesdeJrxml(jrxml, outputPdf, params);

            // mensaje de confirmacion cuando se genera el pdf correctamente
            Alert ok = new Alert(Alert.AlertType.INFORMATION, "PDF generado: " + outputPdf);
            ok.showAndWait();
        } catch (JRException e) {
            e.printStackTrace();
            Alert err = new Alert(Alert.AlertType.ERROR, "Error al generar PDF: " + e.getMessage());
            err.showAndWait();
        }
    }
    //Metodo para cerrar la ventana actual al pulsar el boton de "salir"
    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}