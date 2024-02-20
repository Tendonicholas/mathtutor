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

            Menu firstPanel = new Menu(cardLayout, panelContainer);
            Map secondPanel = new Map(cardLayout, panelContainer);

            panelContainer.add(firstPanel, "Menu");
            panelContainer.add(secondPanel, "Map");

            frame.add(panelContainer);
            frame.setVisible(true);
        });
    }
}
