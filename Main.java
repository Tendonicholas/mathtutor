import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Math Tutor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setResizable(false);

            CardLayout cardLayout = new CardLayout();
            JPanel panelContainer = new JPanel(cardLayout);

            Menu menu = new Menu(cardLayout, panelContainer);
            Map map = new Map(cardLayout, panelContainer, "images/safari_map.jpg");
            AdditionGame additionGame = new AdditionGame(cardLayout, panelContainer);


            panelContainer.add(menu, "Menu");
            panelContainer.add(map, "Map");
            panelContainer.add(additionGame, "addition_game");
            additionGame.setExerciseCompletionListener(menu);

            frame.setLocationRelativeTo(null);
            frame.add(panelContainer);
            frame.setVisible(true);
        });
    }
}
