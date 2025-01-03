import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInterface {
    private JPanel customerLoginPanel;
    private JTextField custEmailField;
    private JPasswordField custPassword;
    private JButton loginButton;
    private JButton signupButton;
    private JButton backButton;

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JButton registerButton;

    // Placeholder for customer logic classes
    static CustomerLogin custLogin = new CustomerLogin(); // Make sure these classes exist
    static CustomerSignup custSignup = new CustomerSignup();

    public CustomerInterface(CardLayout cardLayout, JPanel cardPanel) {
        // Create the main panel for login screen
        customerLoginPanel = new JPanel();
        customerLoginPanel.setLayout(new GridBagLayout());
        

        

        JPanel backgroundPanel = new BackgroundPanel("C:\\Users\\My\\Downloads\\Car_Rental_Project_24Dec\\CRP24Dec\\src\\CarImage.jpg");
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center the inner panel
        backgroundPanel.setOpaque(true); // Make background transparent so the layout of the inner panel works


        backgroundPanel.setLayout(new GridBagLayout()); // Center the inner panel

        // Create an inner panel with padding and border for the login form
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBorder(new EmptyBorder(30, 50, 40, 50)); // Padding around the panel
        innerPanel.setBackground(Color.WHITE); // White background for the inner panel

        innerPanel.setPreferredSize(new Dimension(300, 270)); 
        innerPanel.setMinimumSize(new Dimension(300, 200));
        innerPanel.setMaximumSize(new Dimension(300, 250));

        // Heading label
        JLabel headingLabel = new JLabel("Customer Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        custEmailField = new JTextField(20);
        custEmailField.setPreferredSize(new Dimension(250, 80)); 

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        custPassword = new JPasswordField(20);
        custPassword.setPreferredSize(new Dimension(250, 80));

        // Buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up Now!");
        backButton = new JButton("Back to Main");

        // Style buttons
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBackground(Color.BLACK); // Black color
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupButton.setFont(new Font("Arial", Font.PLAIN, 16));
        signupButton.setBackground(Color.blue); // Blue color
        signupButton.setForeground(Color.WHITE); // White text
        signupButton.setFocusPainted(false);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.red); // Red color
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = custEmailField.getText();
                String password = new String(custPassword.getPassword());

                boolean loginSuccessful = custLogin.validateLogin(email, password);

                if (loginSuccessful) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    cardLayout.show(cardPanel, "CustomerActions");
                } 
                cardLayout.show(cardPanel, "CustomerActions");
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSignupScreen(cardLayout, cardPanel);
                cardLayout.show(cardPanel, "CustomerSignup");
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
        innerPanel.add(emailLabel);
        innerPanel.add(custEmailField);
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(passwordLabel);
        innerPanel.add(custPassword);
        innerPanel.add(Box.createVerticalStrut(20));
        innerPanel.add(loginButton);
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(signupButton);
        innerPanel.add(Box.createVerticalStrut(10));
        innerPanel.add(backButton);
        innerPanel.add(Box.createVerticalStrut(20));

        backgroundPanel.add(innerPanel);

        // Add login panel to card layout
        cardPanel.add(backgroundPanel, "CustomerLogin");

        new CustomerActionsPanel(cardLayout, cardPanel);
    }

    private void createSignupScreen(CardLayout cardLayout, JPanel cardPanel) {
        JPanel signupPanel = new JPanel();
    signupPanel.setLayout(new GridBagLayout());  // Using GridBagLayout for more control over positioning

    // Background Panel
    JPanel backgroundPanel = new BackgroundPanel("C:\\Users\\My\\Downloads\\Car_Rental_Project_24Dec\\CRP24Dec\\src\\CarImage.jpg");
    backgroundPanel.setLayout(new GridBagLayout());
    backgroundPanel.setOpaque(true);

    // Inner Panel for the form
    JPanel innerPanel = new JPanel();
    innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
    innerPanel.setBorder(new EmptyBorder(30, 50, 40, 50)); // Padding around the panel
    innerPanel.setBackground(Color.WHITE); // White background for the inner panel

    innerPanel.setPreferredSize(new Dimension(350, 400)); // Adjusted size to fit more fields

    // Heading label
    JLabel headingLabel = new JLabel("Customer Signup");
    headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
    headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Name field
    JLabel nameLabel = new JLabel("Full Name:");
    nameField = new JTextField();
    nameField.setMaximumSize(new Dimension(300, 30));

    // Email field
    JLabel emailLabel = new JLabel("Email:");
    emailField = new JTextField();
    emailField.setMaximumSize(new Dimension(300, 30));

    // Password field
    JLabel passwordLabel = new JLabel("Password:");
    passwordField = new JPasswordField();
    passwordField.setMaximumSize(new Dimension(300, 30));

    // Phone field
    JLabel phoneLabel = new JLabel("Phone Number:");
    phoneField = new JTextField();
    phoneField.setMaximumSize(new Dimension(300, 30));

    // Buttons
    registerButton = new JButton("Register");
    backButton = new JButton("Back to Login");

    // Style buttons
    registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
    registerButton.setBackground(Color.GREEN);
    registerButton.setForeground(Color.WHITE);
    registerButton.setFocusPainted(false);
    registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    backButton.setFont(new Font("Arial", Font.PLAIN, 16));
    backButton.setBackground(Color.BLUE);
    backButton.setForeground(Color.GRAY);
    backButton.setFocusPainted(false);
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Action Listeners for Buttons
    registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String phone = phoneField.getText();

            try {
                // Check if email is already registered
                if (custSignup.isEmailRegistered(email)) {
                    JOptionPane.showMessageDialog(null, "This email is already registered. Please use a different email.");
                    return;
                }

                // Validate customer details
                if (custSignup.validateCustomerDetails(name, email, password, phone)) {
                    Customer newCustomer = custSignup.registerNewCustomer(name, email, password, phone);

                    if (newCustomer != null) {
                        JOptionPane.showMessageDialog(null, "Registration successful! Your new customer ID is: " + newCustomer.toString() + ".\nPlease login.");
                        cardLayout.show(cardPanel, "CustomerActions");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid details provided. Please check and re-enter.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    });

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "CustomerLogin");
        }
    });

    // Add components to the inner panel
    innerPanel.add(Box.createVerticalStrut(10));
    innerPanel.add(headingLabel);
    innerPanel.add(Box.createVerticalStrut(20));

    innerPanel.add(nameLabel);
    innerPanel.add(nameField);
    innerPanel.add(Box.createVerticalStrut(10));

    innerPanel.add(emailLabel);
    innerPanel.add(emailField);
    innerPanel.add(Box.createVerticalStrut(10));

    innerPanel.add(passwordLabel);
    innerPanel.add(passwordField);
    innerPanel.add(Box.createVerticalStrut(10));

    innerPanel.add(phoneLabel);
    innerPanel.add(phoneField);
    innerPanel.add(Box.createVerticalStrut(20));

    innerPanel.add(registerButton);
    innerPanel.add(Box.createVerticalStrut(10));
    innerPanel.add(backButton);
    innerPanel.add(Box.createVerticalStrut(20));

    // Add the inner panel to the background panel
    backgroundPanel.add(innerPanel);

    // Add the signup panel to card layout
    cardPanel.add(backgroundPanel, "CustomerSignup");

    // Show the signup screen
    cardLayout.show(cardPanel, "CustomerSignup");
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
}


