

import javax.swing.*;
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
        // Initialize login panel..
        customerLoginPanel = new JPanel();
        customerLoginPanel.setLayout(new BoxLayout(customerLoginPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Customer Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        custEmailField = new JTextField();
        custEmailField.setMaximumSize(new Dimension(300, 30));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        custPassword = new JPasswordField();
        custPassword.setMaximumSize(new Dimension(300, 30));

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up Now!");
        backButton = new JButton("Back to Main");

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
        }
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

        // Layout for the login panel
        customerLoginPanel.add(Box.createVerticalStrut(20));
        customerLoginPanel.add(headingLabel);
        customerLoginPanel.add(Box.createVerticalStrut(10));
        customerLoginPanel.add(emailLabel);
        customerLoginPanel.add(custEmailField);
        customerLoginPanel.add(Box.createVerticalStrut(10));
        customerLoginPanel.add(passwordLabel);
        customerLoginPanel.add(custPassword);
        customerLoginPanel.add(Box.createVerticalStrut(20));
        customerLoginPanel.add(loginButton);
        customerLoginPanel.add(Box.createVerticalStrut(10));
        customerLoginPanel.add(signupButton);
        customerLoginPanel.add(Box.createVerticalStrut(10));
        customerLoginPanel.add(backButton);

        // Add login panel to card layout
        cardPanel.add(customerLoginPanel, "CustomerLogin");

        new CustomerActionsPanel(cardLayout, cardPanel);
    }

    private void createSignupScreen(CardLayout cardLayout, JPanel cardPanel) {
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Customer Signup");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Full Name:");
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(300, 30));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30));

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30));

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField();
        phoneField.setMaximumSize(new Dimension(300, 30));

        registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listeners
        registerButton.addActionListener(new ActionListener(){
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
                        return;  // Stop further processing if email is already registered
                    }

                    // Validate customer details
                    if (custSignup.validateCustomerDetails(name, email, password, phone)) {
                        // Proceed with registration using the signup service
                        Customer newCustomer = custSignup.registerNewCustomer(name, email, password, phone);  // Now this returns the Customer object

                        if (newCustomer != null) {
                            // Show success message if registration was successful
                            String message = "Registration successful! Your new customer ID is: " + newCustomer.toString() + ".\nPlease login.";
                            JOptionPane.showMessageDialog(null, message);
                            cardLayout.show(cardPanel, "CustomerLogin");
                            cardLayout.show(cardPanel, "CustomerActions");  // Navigate back to login screen
                        } else {
                            // If registration failed, show error message
                            JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
                        }
                    } else {
                        // If validation fails, show message
                        JOptionPane.showMessageDialog(null, "Invalid details provided. Please check and re-enter.");
                    }
                 }
                
                catch (InvalidEmailFormatException | EmptyFieldException | InvalidPasswordException | InvalidPhoneNumberException | AlreadyRegisteredEmailException ex) {
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

        // Layout for signup panel
        signupPanel.add(Box.createVerticalStrut(20));
        signupPanel.add(headingLabel);
        signupPanel.add(Box.createVerticalStrut(10));
        signupPanel.add(nameLabel);
        signupPanel.add(nameField);
        signupPanel.add(Box.createVerticalStrut(10));
        signupPanel.add(emailLabel);
        signupPanel.add(emailField);
        signupPanel.add(Box.createVerticalStrut(10));
        signupPanel.add(passwordLabel);
        signupPanel.add(passwordField);
        signupPanel.add(Box.createVerticalStrut(10));
        signupPanel.add(phoneLabel);
        signupPanel.add(phoneField);
        signupPanel.add(Box.createVerticalStrut(20));
        signupPanel.add(registerButton);
        signupPanel.add(Box.createVerticalStrut(10));
        signupPanel.add(backButton);

        // Add signup panel to card layout
        cardPanel.add(signupPanel, "CustomerSignup");
    
    }

}

