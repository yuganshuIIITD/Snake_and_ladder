package com.example.ap_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 613, 712);
        stage.setTitle("Snake and Ladder!");
        stage.setScene(scene);
        stage.setResizable(false);
//        Image icon = new Image("C:\\Users\\DELL\\IdeaProjects\\ap_project\\src\\main\\resources\\com\\example\\ap_project\\snake_icon.png");
//        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}