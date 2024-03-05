package main.java;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AdditionGame extends JPanel {
    private int correctAnswer;
    private final JLabel questionLabel, resultLabel;

    private CircularButton[] answerButtons = new CircularButton[3];

    private ExerciseCompletionListener listener;

    private CardLayout cardLayout;
    private JPanel panelContainer;


    public AdditionGame(CardLayout cardLayout, JPanel panelContainer) {
        setLayout(null);

        this.cardLayout = cardLayout;
        this.panelContainer = panelContainer;


        questionLabel = new JLabel();
        questionLabel.setBounds(250, 50, 100, 50);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new CircularButton("0");
            answerButtons[i].addActionListener(new AnswerButtonListener());
            buttonPanel.add(answerButtons[i]);
        }

        buttonPanel.setBounds(70, 400, 600, 200);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        buttonPanel.setBorder(border);



        updateExercise();




        resultLabel = new JLabel("");
        resultLabel.setBounds(700, 200, 200, 50);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 50));

        JButton toMenuButton = new JButton("Go to Menu");
        toMenuButton.setBounds(450, 730, 100, 30);
        toMenuButton.addActionListener(e -> cardLayout.show(panelContainer, "Menu"));



        add(questionLabel);
        add(buttonPanel);
        add(resultLabel);
        add(toMenuButton);

        setVisible(true);
    }

    public void setExerciseCompletionListener(ExerciseCompletionListener listener) {
        this.listener = listener;
    }

    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int selectedAnswer = Integer.parseInt(clickedButton.getText());
            if (selectedAnswer == correctAnswer) {
                resultLabel.setText("Correct!");
                if (listener != null) {
                    listener.onExerciseCompleted();
                    cardLayout.show(panelContainer, "rewardPage");
                }
            } else {
                resultLabel.setText("Incorrect, try a new one!");
                updateExercise();
            }
        }
    }



    private class CircularButton extends JButton {

        private String buttonText;
        private Image buttonImage;

        public CircularButton(String label) {
            super(label);
            this.buttonImage = new ImageIcon("images/button.png").getImage();
            this.buttonText = label;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

        }





        @Override
        protected void paintComponent(Graphics g) {
            // Ensures the button's text and other features are painted correctly
            super.paintComponent(g);

            // Custom painting code for the circular shape
            g.drawImage(buttonImage, 0, 0, getWidth(), getHeight(), this);

            // Set text properties
            g.setFont(new Font("Arial", Font.BOLD, 50)); // Customize font here
            g.setColor(Color.WHITE); // Customize text color here

            // Draw the text over the image
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(buttonText)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g.drawString(buttonText, x, y);
        }

        public void setButtonText(String newText) {
            this.buttonText = newText;
            repaint(); // Redraw the button with the new text
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(180, 180);
        }
    }


    private void updateExercise() {
        Random rand = new Random();
        int number1 = rand.nextInt(10); // Generate a number between 0 and 9
        int number2 = rand.nextInt(10); // Generate a number between 0 and 9
        correctAnswer = number1 + number2;
        questionLabel.setText("What is " + number1 + " + " + number2 + "? ");

        int correctButtonIndex = rand.nextInt(answerButtons.length); // Assuming there are more than 3 buttons
        Set<Integer> usedAnswers = new HashSet<>(); // To track used answers
        usedAnswers.add(correctAnswer); // Add correct answer to prevent duplication

        for (int i = 0; i < answerButtons.length; i++) {
            if (i == correctButtonIndex) {
                answerButtons[i].setButtonText(String.valueOf(correctAnswer));
                answerButtons[i].setText(String.valueOf(correctAnswer));
            } else {
                int wrongAnswer;
                do {
                    // Adjust range as needed to ensure a diverse set of wrong answers
                    wrongAnswer = correctAnswer + rand.nextInt(7) - 3; // Generate numbers in a broader range around the correct answer
                } while (usedAnswers.contains(wrongAnswer)); // Ensure uniqueness of wrong answers
                answerButtons[i].setText(String.valueOf(wrongAnswer));
                answerButtons[i].setButtonText(String.valueOf(wrongAnswer));
                usedAnswers.add(wrongAnswer); // Add to used answers to avoid repetition
            }
        }
    }

}

