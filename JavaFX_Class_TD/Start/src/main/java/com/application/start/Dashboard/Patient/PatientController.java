package com.application.start.Dashboard.Patient;

import com.application.start.GestionBD.Patient.Check;
import com.application.start.GestionBD.Patient.Get;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

public class PatientController {

   public static Patient patient ;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton afficher;

    @FXML
    private JFXButton modifiy;

    @FXML
    private JFXButton remove;

    @FXML
    void clickBtn(ActionEvent event) throws IOException, SQLException {

        if (event.getSource() == add)
        {
            OpenNewPage("/com/application/start/Patient/add.fxml","Add Patient");
        }

        if (event.getSource() == modifiy)
        {
            if (preparePatient())
                OpenNewPage("/com/application/start/Patient/modifiy.fxml","Modifiy Patient");
        }

        if (event.getSource() == remove)
        {
            if (preparePatient())
                OpenNewPage("/com/application/start/Patient/remove.fxml","Remove Patient");
        }
        if (event.getSource() == afficher)
            OpenNewPage("/com/application/start/Patient/afficher.fxml","Afficher Patients");


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

    private boolean preparePatient() throws SQLException, IOException {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Please enter CIN Patient :");

        TextField inputField = new TextField();
        inputField.setPrefWidth(30);
        dialog.getDialogPane().setContent(inputField);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);

        dialog.setResultConverter(button -> {
            if (button == okButton) {
                return inputField.getText();
            }
            return null;
        });

        Optional<String> InputCIN = dialog.showAndWait();
        if (InputCIN.isPresent()) {
            System.out.println("User entered: " + InputCIN.get());

        }

         int idPatient = Check.checkPatient(InputCIN.get());//Get From BD ;


            if (idPatient < 0) {
                gereNotitication("Patient Don't Exist");
                return false;
            }

            else
            {
                // Prepare Data Patient ;
               patient = Get.getDataPatient(idPatient) ;

            }


         return true ;

    }

    private void gereNotitication(String message) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNNING");
               alert.setHeaderText(message);
               alert.setContentText("");
               alert.showAndWait() ;

    }




}
