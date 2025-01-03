import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRPGui {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Panels for different screens
    private JPanel mainPanel;
    private JPanel customerPanel;
    // Removed managerPanel related variables

    public CRPGui() {
        // Initialize the frame and CardLayout
        frame = new JFrame("McRental - Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add main screen
        JPanel mainPanel = createMainScreen();
        cardPanel.add(mainPanel,"Main");
        // Set the frame visible

        // Removed managerPanel code

        frame.setContentPane(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createMainScreen() {
        JPanel panel = new BackgroundPanel("C:\\Users\\My\\Downloads\\Car_Rental_Project_24Dec\\CRP24Dec\\src\\CarImage.jpg"); 
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering

        // Create a semi-transparent inner panel
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setOpaque(true);
        // Set the size of the inner panel
        innerPanel.setPreferredSize(new Dimension(300, 270)); 
        innerPanel.setMinimumSize(new Dimension(300, 200));
        innerPanel.setMaximumSize(new Dimension(300, 250));

        // Set up the text and radio buttons
        JLabel headingLabel = new JLabel("Welcome to McRental!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subheadingLabel = new JLabel("Select your role:");
        subheadingLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subheadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JRadioButton adminButton = new JRadioButton("Admin");
        JRadioButton customerButton = new JRadioButton("Customer");
        // Removed managerButton

        // Add left padding using EmptyBorder
        adminButton.setBorder(new EmptyBorder(0, 20, 0, 0));  // Top, Left, Bottom, Right
        customerButton.setBorder(new EmptyBorder(0, 20, 0, 0));
        // Removed managerButton padding

        // Create the OK button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 14));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setBackground(Color.BLACK); // Set background color
        okButton.setForeground(Color.WHITE); // Set text color
        okButton.setPreferredSize(new Dimension(220, 40)); // Set button size

        // Create a panel for the radio buttons and use GridLayout for alignment
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridLayout(2, 1, 20, 10)); // 2 rows, 1 column, vertical spacing of 10
        radioButtonPanel.setOpaque(false); // Make panel transparent
        radioButtonPanel.add(adminButton);
        radioButtonPanel.add(customerButton);
        // Removed managerButton from panel

        // Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(adminButton);
        group.add(customerButton);
        // Removed managerButton from group

        // Style radio buttons
        adminButton.setOpaque(false);
        customerButton.setOpaque(false);
        adminButton.setFont(new Font("Arial", Font.PLAIN, 16));
        customerButton.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add an ActionListener to the OK button
        okButton.addActionListener(e -> {
            if (adminButton.isSelected()) {
                loadAdminInterface();
            } else if (customerButton.isSelected()) {
                loadCustomerInterface();
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a role before proceeding.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Add components to the inner panel
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(headingLabel);
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(subheadingLabel);
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(radioButtonPanel); // Add the radio button panel
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(okButton);
        innerPanel.add(Box.createVerticalStrut(20));

        // Add the inner panel to the main panel
        panel.add(innerPanel);

        return panel;
    }

    private void loadAdminInterface() {
        AdminInterface adminInterface = new AdminInterface(cardLayout, cardPanel);
        cardLayout.show(cardPanel,"AdminLogin");     
    }

    private void loadCustomerInterface(){
        CustomerInterface customerInterface = new CustomerInterface(cardLayout,cardPanel);
        cardLayout.show(cardPanel,"CustomerLogin");
    }

    // Removed ManagerInterface method

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRPGui::new);
    }
}
