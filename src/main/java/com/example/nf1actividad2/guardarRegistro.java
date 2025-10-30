package com.example.nf1actividad2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

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
        if (chkConfirmar.isSelected()) {
            System.out.println("Registro guardado correctamente:");
            System.out.println("Matrícula: " + lblMatricula.getText());
            System.out.println("Marca: " + lblMarca.getText());
            System.out.println("Año: " + lblAnio.getText());
            System.out.println("Valor: " + lblValor.getText());
        } else {
            System.out.println("Debes confirmar el registro antes de guardar.");
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}