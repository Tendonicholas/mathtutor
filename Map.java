import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Map extends JPanel {
    private final Image backgroundImage;


    Map(CardLayout cardLayout, JPanel panelContainer, String filename) {
        this.setLayout(null);
        backgroundImage = new ImageIcon(filename).getImage();

        //Exercise buttons
        JButton firstExercise = new JButton("firstExercise");
        firstExercise.setBounds(80, 100, 100, 30);

        // Add ActionListener to the button
        firstExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(panelContainer, "addition_game");
            }
        });

        JButton secondExercise = new JButton("secondExercise");
        secondExercise.setBounds(300, 165, 100, 30);

        JButton thirdExercise = new JButton("thirdExercise");
        thirdExercise.setBounds(500, 300, 100, 30);

        JButton nextGrade = new JButton("next grade");
        nextGrade.setBounds(250, 400, 100, 30);

        JButton toMenuButton = new JButton("Go to Menu");
        toMenuButton.setBounds(250, 530, 100, 30);
        toMenuButton.addActionListener(e -> cardLayout.show(panelContainer, "Menu"));


        add(toMenuButton);
        add(firstExercise);
        add(secondExercise);
        add(thirdExercise);
        add(nextGrade);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
