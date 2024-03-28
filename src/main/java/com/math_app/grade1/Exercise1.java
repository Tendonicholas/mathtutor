package com.math_app.grade1;

import com.math_app.DatabaseUtil;
import com.math_app.SceneChanger;
import com.math_app.SessionManager;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Exercise1 {

    public ImageView backButton;
    public Label number1, number2, number3;

    private final List<Label> answerButtons = new ArrayList<>();
    public Label questionLabel;
    public Label resultLabel;

    private int correctAnswer;

    public void initialize() {

        answerButtons.add(number1);
        answerButtons.add(number2);
        answerButtons.add(number3);

        updateExercise();

        backButton.setOnMouseClicked(event -> {
            // Get the current stage (window) from the backButton
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "map.fxml", "Map");
        });

        answerButtons.forEach(label -> {
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Label clickedLabel = (Label) event.getSource();
                    // Assuming you have a way to determine the correct answer from the label
                    int selectedAnswer = Integer.parseInt(clickedLabel.getText());
                    if (selectedAnswer == correctAnswer) {
                        resultLabel.setText("Correct!");
                            // Proceed with additional logic for correct answer
                            Stage stage = (Stage) label.getScene().getWindow();
                            SceneChanger.changeScene(stage, "map.fxml", "Map");
                            System.out.println("Completed");
                            addProgress();

                    } else {
                        resultLabel.setText("Incorrect, try a new one!");
                        // Assuming updateExercise is a method that refreshes the question or exercise
                        updateExercise();
                    }
                }
            });
        });
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




    private void updateExercise() {
        Random rand = new Random();
        int number1 = rand.nextInt(10); // Generate a number between 0 and 9
        int number2 = rand.nextInt(10); // Generate a number between 0 and 9
        correctAnswer = number1 + number2;
        questionLabel.setText(number1 + " + " + number2 + " ?");

        int correctButtonIndex = rand.nextInt(answerButtons.size()); // Assuming there are more than 3 buttons
        Set<Integer> usedAnswers = new HashSet<>(); // To track used answers
        usedAnswers.add(correctAnswer); // Add correct answer to prevent duplication

        for (int i = 0; i < answerButtons.size(); i++) {
            if (i == correctButtonIndex) {
                answerButtons.get(i).setText(String.valueOf(correctAnswer));
            } else {
                int wrongAnswer;
                do {
                    // Adjust range as needed to ensure a diverse set of wrong answers
                    wrongAnswer = correctAnswer + rand.nextInt(7) - 3; // Generate numbers in a broader range around the correct answer
                } while (usedAnswers.contains(wrongAnswer)); // Ensure uniqueness of wrong answers
                answerButtons.get(i).setText(String.valueOf(wrongAnswer));
                usedAnswers.add(wrongAnswer); // Add to used answers to avoid repetition
            }
        }
    }
}
