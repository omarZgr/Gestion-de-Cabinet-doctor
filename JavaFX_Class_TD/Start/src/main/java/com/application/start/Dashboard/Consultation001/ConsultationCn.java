package com.application.start.Dashboard.Consultation001;

import com.application.start.Dashboard.Patient.Patient;
import com.application.start.GestionBD.Patient.Check;
import com.application.start.GestionBD.Patient.Get;
import com.application.start.GestionBD.prepare;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsultationCn {


    @FXML
    private AnchorPane zoneOutput;

    @FXML
    private JFXTextField inputCin;



    @FXML
    private TextField outputDNaissance;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPrenom;

    @FXML
    private TextField outputTel;

    @FXML
    void ajouter(MouseEvent event) throws IOException {

        if (zoneOutput.isDisable())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warrning");
            alert.setHeaderText("Select Patient");
            alert.setContentText("");
            alert.showAndWait() ;
        }
        else
            OpenNewPage("/com/application/start/Consultation/newConsultation.fxml","New Consultation") ;

    }

    private void OpenNewPage(String path,String tilte) throws IOException {

        Stage stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL);
        URL fxmlfile = getClass().getResource(path) ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle(tilte);
        stage.setScene(new Scene(root));
        stage.show();
    }

     public static Patient patient ;

    @FXML
    void search(MouseEvent event) throws SQLException {

        String cin = inputCin.getText() ;

        if (cin.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warrning");
            alert.setHeaderText("Input Empty");
            alert.setContentText("");
            alert.showAndWait() ;
        }
        else
        {
            int idPatient = Check.checkPatient(cin);//GET FROM DB

            if (idPatient<0)
            {
                zoneOutput.setDisable(true);
                {
                    outputNom.setText("");
                    outputTel.setText("");
                    outputPrenom.setText("");
                    outputDNaissance.setText("");
                }
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warrning");
                alert.setHeaderText("Patient introuvable");
                alert.setContentText("");
                alert.showAndWait() ;
            }
            else
            {
                // SET DATA TO OUTPUT

                zoneOutput.setDisable(false);

                 patient = Get.getDataPatient(idPatient) ;
                outputNom.setText(patient.getNom());
                outputPrenom.setText(patient.getPrenom());
                outputTel.setText(patient.getTel());
                outputDNaissance.setText(patient.getDateNaissance());
            }


        }

    }

}
