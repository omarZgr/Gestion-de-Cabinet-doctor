package com.application.start.Dashboard.User.Add;

import com.application.start.GestionBD.prepare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addUser {

    @FXML
    private JFXButton add;

    @FXML
    private JFXTextField inputCIN;

    @FXML
    private JFXTextField inputNom;

    @FXML
    private JFXTextField inputPassword;

    @FXML
    private JFXTextField inputPrenom;

    @FXML
    private JFXTextField inputUserName;

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
            inputPassword.setText("");
            inputCIN.setText("");
            inputUserName.setText("");
        }

        if (event.getSource() == add)
            prepareAddPatient(event) ;

    }

    private void prepareAddPatient(ActionEvent event) throws SQLException {
        String nom = inputNom.getText();
        String prenom = inputPrenom.getText();
        String userName = inputUserName.getText();
        String password = inputPassword.getText();
        String cin = inputCIN.getText();

        if (nom.isEmpty() || prenom.isEmpty() || userName.isEmpty() || password.isEmpty()|| cin.isEmpty() )
            gere_Notification("Input Empty") ;
        else
        {
            User user = new User(nom,prenom,userName,password,cin) ;

            insertUser(user,event) ;

        }

    }

    private void insertUser(User user, ActionEvent event) throws SQLException {

        String nom = user.getNom();
        String prenom = user.getPrenom();
        String userName = user.getUserName();
        String password = user.getPassword();
        String cin = user.getCin();
        String dateCreation = user.getDateCreation() ;

        Connection etat = prepare.getConnection() ;

        String cmdSQL = "Insert Into Users(user_nom,user_prenom,user_cin,user_name,user_password,user_dateInscrrire) values(?,?,?,?,?,?)" ;
        PreparedStatement ps = etat.prepareStatement(cmdSQL) ;

        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,cin);
        ps.setString(4,userName);
        ps.setString(5, password);
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
