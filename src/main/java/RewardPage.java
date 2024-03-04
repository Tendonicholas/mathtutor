import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RewardPage extends JPanel {

    private JLabel rewardText;

    private JButton continueButton;

    private Image backgroundImage;

    private JLabel badgeLabel;
    public RewardPage(CardLayout cardLayout, JPanel panelContainer){
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);

        setLayout(null);


        rewardText = new JLabel();
        rewardText.setBounds(250, 50, 100, 50);

        rewardText.setBorder(border);

        badgeLabel = new JLabel(new ImageIcon("images/crocodil.png"));

        badgeLabel.setBounds(500, 300, 100, 50);

        backgroundImage = new ImageIcon("images/safari_map.jpg").getImage();

        continueButton = new JButton("Continue");
        continueButton.setBounds(70, 400, 600, 200);
        continueButton.addActionListener(e -> cardLayout.show(panelContainer, "Map"));


        add(rewardText);
        add(badgeLabel);
        add(continueButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
