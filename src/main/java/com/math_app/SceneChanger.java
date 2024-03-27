package com.math_app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneChanger {

    /**
     * Changes the current scene to the one specified by the FXML file.
     * @param stage The current stage (window) where the scene will be set.
     * @param fxmlFilePath The path to the FXML file for the new scene.
     * @param title The title for the new scene's window.
     */
    public static void changeScene(Stage stage, String fxmlFilePath, String title) {
        try {
            // Load the new scene
            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxmlFilePath));
            Parent root = loader.load();

            // Create a new scene with the loaded content
            Scene scene = new Scene(root);

            // Set the new scene on the current stage
            stage.setScene(scene);

            // Set the stage title
            stage.setTitle(title);

            // Show the updated stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

