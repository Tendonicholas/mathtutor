import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel implements ExerciseCompletionListener{
    private final JProgressBar progressBar;
    Menu(CardLayout cardLayout, JPanel panelContainer) {

        JButton toSecondPageButton = new JButton("Start");
        toSecondPageButton.addActionListener(e -> cardLayout.show(panelContainer, "Map"));

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // This will show the progress percentage


        add(progressBar);
        add(toSecondPageButton);
    }

    @Override
    public void onExerciseCompleted() {
        // Update the progress bar
        int currentValue = progressBar.getValue();
        progressBar.setValue(currentValue + 10); // Increment by 10, for example
    }

}
