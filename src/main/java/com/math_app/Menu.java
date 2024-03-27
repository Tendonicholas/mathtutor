package com.math_app;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Menu{

    @FXML
    public ImageView playButton;
    public ImageView userAvatar;
    public Label titleLabel;
    public ProgressBar progressBar;

    @FXML
    private Label gradeLabel;


    @FXML
    public void initialize() {

        String username = SessionManager.getInstance().getCurrentUsername();
        if (username != null && !username.isEmpty()) {
            fetchAndDisplayUserInfo(username);
        }


        playButton.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) playButton.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "map.fxml", "Map");
        });


    }



    // Method to display the User information, adjust as needed
    // Fetches user information from the database and updates the UI
    private void fetchAndDisplayUserInfo(String currentUsername) {
        if (currentUsername != null) {
            try (Connection con = DriverManager.getConnection(DatabaseConfig.getUrl(), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
                 PreparedStatement pstmt = con.prepareStatement("SELECT username, grade, avatar, progress FROM users WHERE username = ?")) {

                pstmt.setString(1, currentUsername);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String userName = rs.getString("username");
                        int grade = rs.getInt("grade");
                        String avatar = rs.getString("avatar");
                        double progress = rs.getDouble("progress");

                        // Set user info on the UI
                        titleLabel.setText("Learning math with " + userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase());
                        if(grade == 0){
                            gradeLabel.setText("Grade: K");
                        }else{
                            gradeLabel.setText("Grade: " + grade);
                        }

                        Image image = new Image(getClass().getResourceAsStream(avatar));
                        userAvatar.setImage(image);

                        progressBar.setProgress(progress);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error fetching user data.");
            }
        }
    }


}
