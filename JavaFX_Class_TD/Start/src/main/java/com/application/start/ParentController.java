package com.application.start;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParentController implements Initializable {

    @FXML
    private JFXButton acceuil;

    @FXML
    private JFXButton page2;

    @FXML
    private JFXButton page3;

    @FXML
    private JFXButton page4;

    @FXML
    private JFXButton quitter;

    @FXML
    private BorderPane parent;

    @FXML
    void deplacerMenu(ActionEvent event) throws IOException {
        if (event.getSource() == acceuil)
            preparePage("/com/application/start/Patient/patient.fxml") ;
        else
        if (event.getSource() == page2)
            preparePage("/com/application/start/Consultation/Consultation.fxml") ;
        else
        if (event.getSource() == page3)
            preparePage("/com/application/start/User/User.fxml") ;
        else
            if (event.getSource()==page4)
            {
                System.out.println("dssdsdsdsd");
                preparePage("page4.fxml") ;
            }




    }

    private void preparePage(String path) throws IOException {
        AnchorPane view = FXMLLoader.load((getClass().getResource(path))) ;
        parent.setCenter(view);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            preparePage("/com/application/start/Patient/patient.fxml") ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
