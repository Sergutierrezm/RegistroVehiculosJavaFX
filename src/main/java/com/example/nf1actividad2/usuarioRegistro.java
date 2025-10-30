package com.example.nf1actividad2;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class usuarioRegistro {

    @FXML
    private ComboBox<String> comboMarca;

    @FXML
    private Slider sliderAnio;

    @FXML
    private Label lblAnioSeleccionado;

    @FXML
    private Label lblValorVehiculo;

    @FXML
    private TextField txtMatricula;

    @FXML
    private Button btnSiguiente;

    @FXML
    public void initialize() {
        // Añadir marcas al ComboBox
        comboMarca.getItems().addAll("Audi", "BMW", "Toyota");

        // Mostrar el año seleccionado en el Slider
        sliderAnio.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblAnioSeleccionado.setText(String.valueOf(newVal.intValue()));
            calcularValor();
        });

        // Cuando cambia la marca, recalculamos el valor
        comboMarca.setOnAction(e -> calcularValor());

        // Acción del botón Siguiente
        btnSiguiente.setOnAction(e -> {
            try {
                irAGuardar((ActionEvent) e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void calcularValor() {
        String marca = comboMarca.getValue();
        if (marca == null) return;

        int anio = (int) sliderAnio.getValue();
        int anioActual = 2025;
        int antiguedad = anioActual - anio;

        double precioBase;
        double depreciacion;

        switch (marca) {
            case "BMW":
                precioBase = 40000;
                depreciacion = 1000;
                break;
            case "Audi":
                precioBase = 35000;
                depreciacion = 900;
                break;
            case "Toyota":
                precioBase = 25000;
                depreciacion = 700;
                break;
            default:
                return;
        }

        double valor = precioBase - (antiguedad * depreciacion);
        if (valor < 0) valor = 0;

        lblValorVehiculo.setText(String.format("%.2f €", valor));
    }

    @FXML
    private void irAGuardar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("guardar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Pasar los datos al controlador de guardar
        guardarRegistro controller = fxmlLoader.getController();
        controller.setDatos(
                txtMatricula.getText(),
                comboMarca.getValue(),
                (int) sliderAnio.getValue(),
                lblValorVehiculo.getText()
        );

        // Obtener el Stage actual y cambiar la escena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}