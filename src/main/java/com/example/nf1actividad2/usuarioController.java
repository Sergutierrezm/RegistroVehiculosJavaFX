package com.example.nf1actividad2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class usuarioController {

    @FXML
    //Metodo que se ejecuta cuando el usuario pulsa en registrar vehiculo

    private void registrarVehiculo(ActionEvent event) {
        try {
            // Cargamos el FXML de la siguiente pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    //Metodo que se ejecuta cuando el usuario pulsa en salir
    private void salir(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}