package com.math_app;
import com.math_app.DatabaseUtil;
import com.math_app.SceneChanger;
import com.math_app.SessionManager;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tutorial {

    public ImageView backButton;
    @FXML
    private WebView webView;

    private String videoUrl;

    public void initialize() {

        WebEngine webEngine = webView.getEngine();
        // Use the embed URL, which you can obtain from the YouTube video's share option
        webEngine.load(videoUrl);
        backButton.setDisable(true);


        backButton.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) backButton.getScene().getWindow();

            webView.getEngine().load("about:blank");

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "map.fxml", "Map");

        });

        PauseTransition pause = new PauseTransition(Duration.seconds(10)); // 10 seconds delay
        pause.setOnFinished(event ->{
            addProgress();
            backButton.setDisable(false);
        });
        pause.play();
    }

    private void addProgress(){
        String username = SessionManager.getInstance().getCurrentUsername();
        String query ="UPDATE users SET progress = progress + 0.10 WHERE username = ?;";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, username);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to set the video URL and load the video
    public void setAndLoadVideoUrl(String url) {
        this.videoUrl = url;
        loadVideo();
    }

    // Loads the video into the WebView
    private void loadVideo() {
        if (videoUrl != null && webView != null) {
            WebEngine webEngine = webView.getEngine();
            webEngine.load(videoUrl);
        } else {
            System.out.println("WebView is not initialized or URL is null.");
        }
    }
}
