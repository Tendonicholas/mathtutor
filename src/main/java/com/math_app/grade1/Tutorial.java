package com.math_app.grade1;
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

    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        // Use the embed URL, which you can obtain from the YouTube video's share option
        String videoUrl = "https://www.youtube.com/embed/L0Oq1xqCQss?autoplay=1"; // Link to the video
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
}
