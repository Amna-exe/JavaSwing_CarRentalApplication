import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminInterface {
    private JPanel adminLoginPanel;
    private JTextField adminIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    AdminLogin adLogin = new AdminLogin();

    public AdminInterface(CardLayout cardLayout, JPanel cardPanel) {


        // Create the main panel with background color
        adminLoginPanel = new JPanel();
        adminLoginPanel.setLayout(new GridBagLayout());
        adminLoginPanel.setOpaque(false); // Make the main panel transparent



        JPanel backgroundPanel = new BackgroundPanel("C:\\Users\\My\\Downloads\\Car_Rental_Project_24Dec\\CRP24Dec\\src\\CarImage.jpg");
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center the inner panel
        backgroundPanel.setOpaque(true); // Make background transparent so the layout of the inner panel works

        // Create an inner panel with padding and border for the login form
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBorder(new EmptyBorder(20, 40, 30, 40)); // Padding around the panel
        innerPanel.setBackground(Color.WHITE); // White background for the inner panel

        innerPanel.setPreferredSize(new Dimension(300, 270)); 
        innerPanel.setMinimumSize(new Dimension(300, 200));
        innerPanel.setMaximumSize(new Dimension(300, 250));

        // Heading label
        JLabel headingLabel = new JLabel("Admin Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Admin ID label and field
        JLabel adminIdLabel = new JLabel("Admin ID:");
        adminIdLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        adminIdLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        adminIdField = new JTextField(20);
        adminIdField.setPreferredSize(new Dimension(250, 80)); 

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(250, 80));

        // Buttons
        loginButton = new JButton("Login");
        backButton = new JButton("Back to Main");

        // Style buttons
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBackground(Color.BLACK); // Blue color
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.blue); // Red color
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    // Validate credentials
                    if (adLogin.validateLogin(adminId, password)) {
                        JOptionPane.showMessageDialog(adminLoginPanel, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearFields();  // Clear fields after successful login
                        cardLayout.show(cardPanel, "AdminActions");
                    } else {
                        throw new InvalidAdminCredentialsException("Invalid Admin ID or Password. Please try again.");
                    }
                } catch (InvalidAdminCredentialsException ex) {
                    JOptionPane.showMessageDialog(adminLoginPanel, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
            private void clearFields() {
                adminIdField.setText("");
                passwordField.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main");
            }
        });


        // Add components to the inner panel
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(headingLabel);
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(adminIdLabel);
        innerPanel.add(adminIdField);
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(passwordLabel);
        innerPanel.add(passwordField);
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(loginButton);
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(backButton);
        innerPanel.add(Box.createVerticalStrut(20));

        backgroundPanel.add(innerPanel);


        // Add login panel to card layout
        cardPanel.add(backgroundPanel, "AdminLogin");

        // Create and add the Admin Actions screen
        new AdminActionsGui(cardLayout, cardPanel);
    }

    private static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);  // Ensure image scales to the panel size
            }
        }

    }

    class InvalidAdminCredentialsException extends Exception {
        public InvalidAdminCredentialsException(String message) {
            super(message);
        }
    }
}
