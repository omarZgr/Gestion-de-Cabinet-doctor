package com.application.start.Dashboard.Patient.Modifiy;

import com.application.start.Dashboard.Patient.PatientController;
import com.application.start.GestionBD.prepare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class modifiyPatient implements Initializable {

    @FXML
    private JFXButton modifiy;

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
            getData();
        else
            prepareModifiyPatient(event) ;

    }

    private void prepareModifiyPatient(ActionEvent event) throws SQLException {
        String tel = inputTel.getText() ;
        if (tel.isEmpty())
            gere_Notification("input Tele Empty !") ;
        else
        {
            Connection etat = prepare.getConnection() ;
            String cmdSQL = "Update patient set patient_tel=? where patient_id=?" ;
            PreparedStatement ps=etat.prepareStatement(cmdSQL) ;
            ps.setString(1,tel);
            ps.setInt(2, PatientController.patient.getId());

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

    private void gere_Notification(String txt)
    {
        notification.setText(txt);
        notification.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> notification.setVisible(false));
        pause.play();

    }

    private void returnInsert(int result,ActionEvent event) throws SQLException {
        if (result >0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Modifiy User Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Probleme in Modifiy User ");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }


}
