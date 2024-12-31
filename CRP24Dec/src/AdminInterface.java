import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class AdminInterface {
  private JPanel adminLoginPanel;
    private JTextField adminIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    AdminLogin adLogin = new AdminLogin();

    public AdminInterface(CardLayout cardLayout, JPanel cardPanel) {
        adminLoginPanel = new JPanel();
        adminLoginPanel.setLayout(new BoxLayout(adminLoginPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Admin Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel adminIdLabel = new JLabel("Admin ID:");
        adminIdField = new JTextField(20);
        adminIdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        loginButton = new JButton("Login");
        backButton = new JButton("Back to Main");

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    // Validate credentials
                    if (adLogin.validateLogin(adminId, password)) {
                      JOptionPane.showMessageDialog(adminLoginPanel, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                      // Redirect to the Admin Actions panel after successful login
                      cardLayout.show(cardPanel, "AdminActions"); // Admin responsibilities screen
                  } else {
                      // If invalid, throw the custom exception
                      throw new InvalidAdminCredentialsException("Invalid Admin ID or Password. Please try again.");
                  }
                } catch (InvalidAdminCredentialsException ex) {
                    JOptionPane.showMessageDialog(adminLoginPanel, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main");
            }
        });

        
        // Layout for the login panel
        adminLoginPanel.add(Box.createVerticalStrut(20));
        adminLoginPanel.add(headingLabel);
        adminLoginPanel.add(Box.createVerticalStrut(20));
        adminLoginPanel.add(adminIdLabel);
        adminLoginPanel.add(adminIdField);
        adminLoginPanel.add(Box.createVerticalStrut(10));
        adminLoginPanel.add(passwordLabel);
        adminLoginPanel.add(passwordField);
        adminLoginPanel.add(Box.createVerticalStrut(20));
        adminLoginPanel.add(loginButton);
        adminLoginPanel.add(Box.createVerticalStrut(10));
        adminLoginPanel.add(backButton);

        // Add login panel to card layout
        cardPanel.add(adminLoginPanel, "AdminLogin");

        // Create and add the Admin Actions screen
        new AdminActionsGui(cardLayout, cardPanel);
    }

    class InvalidAdminCredentialsException extends Exception {
      public InvalidAdminCredentialsException(String message) {
          super(message);
      }
  }

}
