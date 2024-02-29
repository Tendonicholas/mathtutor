import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AdditionGame extends JPanel {
    private int correctAnswer;
    private final JLabel questionLabel, resultLabel;

    private CircularButton[] answerButtons = new CircularButton[3];

    private ExerciseCompletionListener listener;


    public AdditionGame(CardLayout cardLayout, JPanel panelContainer) {
        setLayout(null);


        questionLabel = new JLabel();
        questionLabel.setBounds(250, 50, 100, 50);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new CircularButton("0");
            answerButtons[i].addActionListener(new AnswerButtonListener());
            buttonPanel.add(answerButtons[i]);
        }

        buttonPanel.setBounds(200, 100, 200, 100);


        updateExercise();




        resultLabel = new JLabel("");
        resultLabel.setBounds(220, 200, 150, 50);

        JButton toMenuButton = new JButton("Go to Menu");
        toMenuButton.setBounds(250, 530, 100, 30);
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
                }
            } else {
                resultLabel.setText("Incorrect, try a new one!");
                updateExercise();
            }
        }
    }

    private class CircularButton extends JButton {

        public CircularButton(String label) {
            super(label);
            // These UI adjustments are optional but recommended
            setContentAreaFilled(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Ensures the button's text and other features are painted correctly
            super.paintComponent(g);

            // Custom painting code for the circular shape
            g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
        }

        @Override
        public Dimension getPreferredSize() {
            // Ensures the button has a square shape to maintain the circle's aspect ratio
            Dimension size = super.getPreferredSize();
            int max = Math.max(size.width, size.height);
            setPreferredSize(new Dimension(max, max));
            return new Dimension(max, max);
        }
    }


    private void updateExercise() {
        Random rand = new Random();
        int number1 = rand.nextInt(10); // Generate a number between 0 and 49
        int number2 = rand.nextInt(10); // Generate a number between 0 and 49
        correctAnswer = number1 + number2;
        questionLabel.setText("What is " + number1 + " + " + number2 + "? ");

        int correctButtonIndex = rand.nextInt(3);
        for (int i = 0; i < answerButtons.length; i++) {
            if (i == correctButtonIndex) {
                answerButtons[i].setText(String.valueOf(correctAnswer));
            } else {
                // Generate a random number for the button, ensuring it is not the correct answer
                int wrongAnswer;
                do {
                    wrongAnswer = correctAnswer + rand.nextInt(3) - 3; // Generate numbers in a range around the correct answer
                } while (wrongAnswer == correctAnswer);
                answerButtons[i].setText(String.valueOf(wrongAnswer));
            }
        }
    }

}

