package com.application.start.Dashboard.Patient.Afficher;

import com.application.start.Dashboard.Patient.Patient;
import com.application.start.GestionBD.Patient.Get;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class afficherPatients implements Initializable {

    @FXML
    private TableColumn<Patient, String> CIN;

    @FXML
    private TableColumn<Patient, String> Nom;

    @FXML
    private TableColumn<Patient, String> Prenom;

    @FXML
    private TableColumn<Patient, String> Tel;

    @FXML
    private TableView<Patient> Table;

    @FXML
    private TableColumn<Patient, String> dateNaissance;

    @FXML
    private JFXButton ok;

    @FXML
    void clicikBtn(ActionEvent event) {

        if (event.getSource()==ok)
        {
            Stage oldStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            oldStage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getData() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getData() throws SQLException {
        ArrayList<Patient>  data = Get.getData();//Get from BD ;
        linkWith_Table(data);
    }

    private void linkWith_Table(ArrayList<Patient> dataListe)
    {
        if (dataListe != null)
        {
            Nom.setCellValueFactory(new PropertyValueFactory<Patient, String>("nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<Patient, String>("prenom"));
            dateNaissance.setCellValueFactory(new PropertyValueFactory<Patient,String>("dateNaissance"));
            CIN.setCellValueFactory(new PropertyValueFactory<Patient,String>("cin"));
            Tel.setCellValueFactory(new PropertyValueFactory<Patient,String>("tel"));
            Table.getItems().setAll(dataListe) ;

        }

    }
}
