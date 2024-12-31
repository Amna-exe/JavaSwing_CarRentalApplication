import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class ManagerInterface {
    
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public ManagerInterface(JFrame frame, JPanel cardPanel, CardLayout cardLayout) {
        this.frame = frame;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
    }

    public ManagerInterface(CardLayout cardLayout2, JPanel cardPanel2) {
        //TODO Auto-generated constructor stub
    }

    public void loadScreen() {
        JPanel managerPanel = new JPanel();
        managerPanel.setLayout(new BoxLayout(managerPanel, BoxLayout.Y_AXIS));

        JLabel managerLabel = new JLabel("Manager Interface");
        managerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        managerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        managerPanel.add(Box.createVerticalStrut(20));
        managerPanel.add(managerLabel);
        managerPanel.add(Box.createVerticalStrut(20));
        managerPanel.add(backButton);

        cardPanel.add(managerPanel, "Manager");
        cardLayout.show(cardPanel, "Manager");
    
}

}
