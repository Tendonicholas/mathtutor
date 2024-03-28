package com.math_app.grade1;

import com.math_app.SceneChanger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Grade1_Map {
    public ImageView backButton;
    public Label tutorial1;
    public Label firstExercise;
    public Label tutorial2;
    public Label exercise2;

    @FXML
    public void initialize() {
        backButton.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "menu.fxml", "Menu");
        });

        tutorial1.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) tutorial1.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeSceneWithParameters(stage, "tutorial.fxml", "Tutorial", "https://www.youtube.com/embed/L0Oq1xqCQss?autoplay=1");
        });

        tutorial2.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) tutorial1.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeSceneWithParameters(stage, "tutorial.fxml", "Tutorial", "https://www.youtube.com/embed/AsxhyscqQzM?autoplay=1");
        });

        firstExercise.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) firstExercise.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "exercise1.fxml", "FirstEx");
        });

        exercise2.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) firstExercise.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "exercise2.fxml", "SecondEx");
        });
    }

}
