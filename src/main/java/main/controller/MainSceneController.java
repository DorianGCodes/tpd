package main.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    public static boolean directed = false, undirected = true, weighted = false, unweighted = true;

    @FXML
    public RadioButton directedButton, undirectedButton, weightedButton, unweightedButton;

    @FXML
    public Button nextSceneButton;

    static NodeSceneController nodeSceneController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directedButton.setSelected(directed);
        undirectedButton.setSelected(undirected);
        weightedButton.setSelected(weighted);
        unweightedButton.setSelected(unweighted);

        nextSceneButton.setOnAction(e -> {
            moveToNextScene();
        });

    }

    void moveToNextScene() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));

        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(e -> {
            loadNextScene();
        });
        ft.play();
        System.out.println("Here");
    }

    void loadNextScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("NodeScene.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            nodeSceneController = loader.getController();

//            System.out.println("Controller ref: " + cref);
//            newScene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
            MainApp.primaryStage.setScene(newScene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
