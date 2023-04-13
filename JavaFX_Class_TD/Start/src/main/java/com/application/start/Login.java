package com.application.start;

import com.application.start.GestionBD.User.checkUser;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Login {

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField userName;

    @FXML
    private Label notification;

    @FXML
    void login(MouseEvent event) throws SQLException, IOException {

        String inputUsetName = userName.getText() ;
        String inputPassword = password.getText() ;

        if (inputUsetName.isEmpty() || inputPassword.isEmpty())
            gere_Notification("Input Empty") ;
        else
        {
            int etat = checkUser.checkUser(inputUsetName,inputPassword) ;


            if (etat==-1)
                gere_Notification("userName or Password Inccorect") ;
            else
                openDashboard(event) ;
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

    private void openDashboard(MouseEvent event) throws IOException {

        Stage stage = new Stage() ;
        URL fxmlfile = getClass().getResource("/com/application/start/Dashboard.fxml") ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
        stage.show();
        Stage oldStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        oldStage.close();

    }

}
