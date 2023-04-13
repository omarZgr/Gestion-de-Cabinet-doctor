package com.application.start.Dashboard.User;

import com.application.start.GestionBD.User.Get;
import com.application.start.GestionBD.User.checkUser;
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

public class UserController {

   public static User user ;

    @FXML
    private JFXButton add;


    @FXML
    private JFXButton modifiy;

    @FXML
    private JFXButton remove;

    @FXML
    void clickBtn(ActionEvent event) throws IOException, SQLException {

        if (event.getSource() == add)
        {
            OpenNewPage("/com/application/start/User/add.fxml","Add User");
        }

        if (event.getSource() == modifiy)
        {
            if (preparePatient())
                OpenNewPage("/com/application/start/User/modifiy.fxml","Modifiy User");
        }

        if (event.getSource() == remove)
        {
            if (preparePatient())
                OpenNewPage("/com/application/start/User/remove.fxml","Remove User");
        }


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
        dialog.setHeaderText("Please enter CIN User :");

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

         int idUser = checkUser.getUser_By_CIN(InputCIN.get());  //Get From BD ;


            if (idUser < 0) {
                gereNotitication("User Don't Exist");
                return false;
            }

            else
            {
                // Prepare Data Patient ;
                user = Get.getDataUser(idUser) ;

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
