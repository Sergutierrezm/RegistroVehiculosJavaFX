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
    private void registrarVehiculo(ActionEvent event) {
        try {
            // Cargamos el FXML de la siguiente pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Parent root = loader.load();

            // Obtenemos el stage actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cambiamos la escena
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        // Cerramos la aplicación
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}