import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends JPanel{
    Menu(CardLayout cardLayout, JPanel panelContainer) {

        JButton toSecondPageButton = new JButton("Start");
        toSecondPageButton.addActionListener(e -> cardLayout.show(panelContainer, "Map"));
        add(toSecondPageButton);
    }
}
