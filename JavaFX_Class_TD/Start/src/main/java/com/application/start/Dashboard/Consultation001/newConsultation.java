package com.application.start.Dashboard.Consultation001;

import com.application.start.GestionBD.prepare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class newConsultation implements Initializable {


    @FXML
    private TextArea inputDescription;

    @FXML
    private JFXButton add;

    @FXML
    private JFXTextField inputPrix;

    @FXML
    private Label notification;

    @FXML
    private JFXTextField outputNom;

    @FXML
    private JFXTextField outputPrenom;

    @FXML
    private JFXButton reset;

    @FXML
    void clicikBtn(ActionEvent event) throws SQLException {

        if (event.getSource()==reset)
        {
            getData() ;
        }
        else
            insertNewConsultation(event);
    }

    private void insertNewConsultation(ActionEvent event) throws SQLException {

        String prix = inputPrix.getText() ;
        String description = inputDescription.getText() ;

        if (prix.isEmpty())
            gere_Notification("Prix input is Empty") ;
        else
        {
            Connection etat = prepare.getConnection() ;
            String insertConsultation  ="insert into Consultation(consultation_descritpion,consultation_prix) values(?,?)";
            PreparedStatement psConsultation=etat.prepareStatement(insertConsultation, Statement.RETURN_GENERATED_KEYS) ;

            psConsultation.setString(1,description);
            psConsultation.setString(2,prix);

            int ConsultationInsertedRows = psConsultation.executeUpdate() ;

            if (ConsultationInsertedRows>0)
            {
                ResultSet personIDResult = psConsultation.getGeneratedKeys();
                if (personIDResult.next())
                {
                    int idConsultation = personIDResult.getInt(1) ;
                    String  insertDemande = "insert into demande(consultation_date,patient_id,consultation_id) values(?,?,?)" ;
                    PreparedStatement psDeamnde = etat.prepareStatement(insertDemande) ;

                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedDate = currentDate.format(formatter);

                    psDeamnde.setString(1,formattedDate);
                    psDeamnde.setInt(2,ConsultationCn.patient.getId());
                    psDeamnde.setInt(3,idConsultation);


                    int result = psDeamnde.executeUpdate() ;

                    returnInsert(result,event) ;





                }
            }



        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData() ;
    }

    private  void getData()
    {
        outputNom.setText(ConsultationCn.patient.getNom());
        outputPrenom.setText(ConsultationCn.patient.getPrenom());
        inputPrix.setText("");
        inputDescription.setText("");
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
