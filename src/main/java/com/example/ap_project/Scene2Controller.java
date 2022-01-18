package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene2Controller {

    @FXML private Stage stage;
    @FXML private Parent root;
    @FXML private Scene scene;
    @FXML private TextField player1;
    @FXML private TextField player2;
    @FXML private  void startgame(ActionEvent e) throws IOException {
        String p1 = player1.getText();
        String p2 = player2.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        root = loader.load();
        Scene3Controller obj = loader.getController();
        obj.display(p1,p2);
        obj.addSnakeLadder();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
