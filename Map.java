import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {

    Map(CardLayout cardLayout, JPanel panelContainer) {
        this.setLayout(null);

        //Exercise buttons
        JButton firstExercise = new JButton("firstExercise");
        firstExercise.setBounds(100, 100, 100, 30);

        JButton secondExercise = new JButton("secondExercise");
        secondExercise.setBounds(200, 200, 100, 30);

        JButton thirdExercise = new JButton("thirdExercise");
        thirdExercise.setBounds(300, 300, 100, 30);

        JButton nextGrade = new JButton("next grade");
        nextGrade.setBounds(400, 400, 100, 30);

        JButton toMenuButton = new JButton("Go to Menu");
        toMenuButton.setBounds(250, 530, 100, 30);
        toMenuButton.addActionListener(e -> cardLayout.show(panelContainer, "Menu"));


        add(toMenuButton);
        add(firstExercise);
        add(secondExercise);
        add(thirdExercise);
        add(nextGrade);
    }
}
