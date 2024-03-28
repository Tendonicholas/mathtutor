package com.math_app.grade1;

import com.math_app.ImageTextPair;
import com.math_app.SceneChanger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.*;

public class Exercise2 {

    public Pane imageContainer;
    public Label number1, number2, number3;

    private final List<Label> answerButtons = new ArrayList<>();
    public Label resultLabel;
    public Label questionLabel;
    public ImageView backButton;

    int numberOfImages = 0;

    List<ImageTextPair> imageTextPairs = new ArrayList<>();

    public void initialize() {

        answerButtons.add(number1);
        answerButtons.add(number2);
        answerButtons.add(number3);


        imageTextPairs.add(new ImageTextPair("src/main/resources/images/animals/elephant.png", "Elephant"));
        imageTextPairs.add(new ImageTextPair("src/main/resources/images/animals/turtle.png", "Turtle"));

        updateExercise();

        answerButtons.forEach(label -> {
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Label clickedLabel = (Label) event.getSource();
                    // Assuming you have a way to determine the correct answer from the label
                    int selectedAnswer = Integer.parseInt(clickedLabel.getText());
                    if (selectedAnswer == numberOfImages) {
                        resultLabel.setText("Correct!");
                        // Proceed with additional logic for correct answer
//                        Stage stage = (Stage) label.getScene().getWindow();
//                        SceneChanger.changeScene(stage, "map.fxml", "Map");
                        System.out.println("Completed");
//                        addProgress();

                    } else {
                        resultLabel.setText("Incorrect, try a new one!");
                        // Assuming updateExercise is a method that refreshes the question or exercise
                        updateExercise();
                    }
                }
            });
        });

        backButton.setOnMouseClicked(event -> {
            // Get the current stage (window) from the playButton
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Call the changeScene method with the necessary parameters
            SceneChanger.changeScene(stage, "map.fxml", "Map");
        });
    }

    private void updateExercise() {
        Platform.runLater(() -> {
            Random random = new Random();
            numberOfImages = random.nextInt(5) + 1; // Generates a random number between 1 and 5

            int index = random.nextInt(imageTextPairs.size());
            ImageTextPair selectedPair = imageTextPairs.get(index);

            // Clear existing images from the container
            imageContainer.getChildren().clear();

            final int imageWidth = 100;
            final int imageHeight = 100;

            // Image dimensions and spacing
            final int spacing = 1; // Spacing between images

            // Calculate total width of all images including spacing
            int totalWidth = numberOfImages * imageWidth + (numberOfImages - 1) * spacing;

            // Calculate the starting X position to center the images within the Pane
            double startX = (imageContainer.getWidth() - totalWidth) / 2;
            double startY = (imageContainer.getHeight() - imageHeight) / 2; // For vertical centering, if desired

            for (int i = 0; i < numberOfImages; i++) {
                ImageView imageView = new ImageView(new Image("file:" + selectedPair.getImagePath()));

                // Set the desired size for the image
                imageView.setFitWidth(imageWidth);
                imageView.setFitHeight(imageHeight);

                // Calculate the X position for this image
                double xPos = startX + i * (imageWidth + spacing);

                // Set the image position
                imageView.setX(xPos); // Adjusted for spacing
                imageView.setY(startY);
                imageContainer.getChildren().add(imageView);
            }

            questionLabel.setText("How many " + selectedPair.getText() + "s ?");

            // Update answers on buttons
            int correctButtonIndex = random.nextInt(answerButtons.size());
            Set<Integer> usedAnswers = new HashSet<>();
            usedAnswers.add(numberOfImages); // Add correct answer to prevent duplication

            for (int i = 0; i < answerButtons.size(); i++) {
                if (i == correctButtonIndex) {
                    answerButtons.get(i).setText(String.valueOf(numberOfImages));
                } else {
                    int wrongAnswer;
                    do {
                        wrongAnswer = random.nextInt(10) + 1; // This range might need adjustment based on your exercise logic
                    } while (usedAnswers.contains(wrongAnswer) || wrongAnswer == numberOfImages); // Ensure wrong answers are unique and not correct
                    answerButtons.get(i).setText(String.valueOf(wrongAnswer));
                    usedAnswers.add(wrongAnswer);
                }
            }
        });
    }


}
