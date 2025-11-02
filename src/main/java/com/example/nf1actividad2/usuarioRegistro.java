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

    //Clase que controla la pantalla de registro de un vehiculo
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
    //metodo que se ejecuta automaticamente
    public void initialize() {
        // Añadimos las marcas disponibles
        comboMarca.getItems().addAll("Audi", "BMW", "Toyota");

        // Escucha los cambios del slider y actualiza la etiqueta del año seleccionado
        //Cada vez que cambia el valor del slider, tambien recalcula el valor del coche
        sliderAnio.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblAnioSeleccionado.setText(String.valueOf(newVal.intValue()));
            calcularValor();
        });

        // Cuando cambia la marca, recalculamos el valor
        comboMarca.setOnAction(e -> calcularValor());

        // Acción del botón Siguiente, llama al metodo irGuardar
        btnSiguiente.setOnAction(e -> {
            try {
                irAGuardar((ActionEvent) e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    //metodo que calcula el valor estimado del vehiculo
    private void calcularValor() {
        String marca = comboMarca.getValue();
        if (marca == null) return;

        int anio = (int) sliderAnio.getValue();
        int anioActual = 2025;
        int antiguedad = anioActual - anio; //se calcula los años de antiguedad

        double precioBase;
        double depreciacion;
        //Asginamos el precio base y la depreciacion anual segun la marca que indiquemos
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
        //calcula el valor final
        double valor = precioBase - (antiguedad * depreciacion);
        if (valor < 0) valor = 0;

        //muestra el valor calculado
        lblValorVehiculo.setText(String.format("%.2f €", valor));
    }

    @FXML
    //metodo que cambia a la pantalla de guardar los datos
    private void irAGuardar(ActionEvent event) throws IOException {
       // carga el archivo fxml de la siguiente pantalla
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("guardar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // envia los datos introducidos por el usuario a la sigueinte ventana
        guardarRegistro controller = fxmlLoader.getController();
        controller.setDatos(
                txtMatricula.getText(),
                comboMarca.getValue(),
                (int) sliderAnio.getValue(),
                lblValorVehiculo.getText()
        );


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}