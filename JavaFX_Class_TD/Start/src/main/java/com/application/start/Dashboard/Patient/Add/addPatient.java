package com.application.start.Dashboard.Patient.Add;

import com.application.start.GestionBD.prepare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class addPatient {

    @FXML
    private JFXButton add;

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
    private Label notification;

    @FXML
    private JFXButton reset;

    @FXML
    void clicikBtn(ActionEvent event) throws SQLException {

        if (event.getSource() == reset)
        {
            inputNom.setText("");
            inputPrenom.setText("");
            inputTel.setText("");
            inputCIN.setText("");
            inputDateNaissance.setValue(null);
        }

        if (event.getSource() == add)
            prepareAddPatient(event) ;

    }

    private void prepareAddPatient(ActionEvent event) throws SQLException {
        String nom = inputNom.getText();
        String prenom = inputPrenom.getText();
        String tel = inputTel.getText();
        String cin = inputCIN.getText();
        LocalDate dateNaissance = inputDateNaissance.getValue() ;

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || cin.isEmpty() )
            gere_Notification("Input Empty") ;
        else
        {
            Patient patient = new Patient(nom,prenom,tel,cin,dateNaissance) ;

            insertPatient(patient,event) ;

        }

    }

    private void insertPatient(Patient patient, ActionEvent event) throws SQLException {

        String nom = patient.getNom();
        String prenom = patient.getPrenom();
        String tel = patient.getTel();
        String cin = patient.getCin();
        LocalDate dateNaissance = patient.getDateNaissance() ;
        String dateCreation = patient.getDateCreation() ;

        Connection etat = prepare.getConnection() ;

        String cmdSQL = "Insert Into Patient(patient_nom,patient_prenom,patient_tel,patient_cin,patient_dateNass,patient_dateCreation) values(?,?,?,?,?,?)" ;
        PreparedStatement ps = etat.prepareStatement(cmdSQL) ;

        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,tel);
        ps.setString(4,cin);
        ps.setDate(5, Date.valueOf(dateNaissance));
        ps.setDate(6, Date.valueOf(dateCreation));

        int result = ps.executeUpdate() ;

        returnInsert(result,event) ;
    }

    private void returnInsert(int result,ActionEvent event) throws SQLException {
        if (result >0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Add User Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Probleme in Add User ");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }

    private void gere_Notification(String txt)
    {
        notification.setText(txt);
        notification.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> notification.setVisible(false));
        pause.play();

    }

}
