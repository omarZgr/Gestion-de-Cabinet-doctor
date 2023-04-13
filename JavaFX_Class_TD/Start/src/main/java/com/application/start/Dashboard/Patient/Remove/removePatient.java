package com.application.start.Dashboard.Patient.Remove;

import com.application.start.Dashboard.Patient.PatientController;
import com.application.start.GestionBD.prepare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class removePatient implements Initializable {

    @FXML
    private JFXTextField inputCIN;

    @FXML
    private DatePicker inputDateNaissance;

    @FXML
    private JFXTextField inputNom;

    @FXML
    private JFXTextField inputPrenom;

    @FXML
    private JFXTextField inputTel;

    @FXML
    private JFXButton remove;

    @FXML
    private Label notification;

    @FXML
    void clicikBtn(ActionEvent event) throws SQLException {

        if (event.getSource()==remove)
        {
            Connection etat = prepare.getConnection() ;
            String cmdSQL = "DELETE from Patient where patient_id=?" ;
            PreparedStatement ps=etat.prepareStatement(cmdSQL) ;
            ps.setInt(1, PatientController.patient.getId());
            int result = ps.executeUpdate() ;

            returnInsert(result,event) ;

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData() ;
    }

    private void getData()
    {
        inputNom.setText(PatientController.patient.getNom());
        inputPrenom.setText(PatientController.patient.getPrenom());
        inputTel.setText(PatientController.patient.getTel());
        inputCIN.setText(PatientController.patient.getCin());
        inputDateNaissance.setValue(LocalDate.parse(PatientController.patient.getDateNaissance()));
    }

    private void returnInsert(int result,ActionEvent event) throws SQLException {
        if (result >0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Remove User Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Probleme in Remove User ");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }


}
