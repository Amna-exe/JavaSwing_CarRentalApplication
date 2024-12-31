import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminActionsGui {
    private JPanel adminActionsPanel = new JPanel();
    

    public AdminActionsGui(CardLayout cardLayout, JPanel cardPanel) {
        adminActionsPanel = new JPanel();
        adminActionsPanel.setLayout(new BoxLayout(adminActionsPanel, BoxLayout.Y_AXIS));

        // Heading label at the top
        JLabel headingLabel = new JLabel("Admin Responsibilities");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add heading label first, so it's at the top
        adminActionsPanel.add(headingLabel);
        adminActionsPanel.add(Box.createVerticalStrut(20)); // Space below the heading

        // List of actions
        String[] actions = {
                "Add a Manager", "Delete a Manager", "Update Manager Details", "View All Managers",
                "Add a Customer", "Delete a Customer", "Update Customer Details", "View All Customers",
                "Add a Car", "Delete a Car", "Update Car Details", "View All Cars",
                "View Profile", "Edit Profile", "Logout"
        };

        // Loop through actions and create buttons
        for (String action : actions) {
            JButton actionButton = new JButton(action);
            actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add ActionListener to each button
            actionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (action.equals("Add a Manager")) {
                        addManager();
                    } else if (action.equals("Delete a Manager")) {
                        deleteManager();
                    } else if (action.equals("Update Manager Details")) {
                        updateManagerDetails();
                    } else if (action.equals("View All Managers")) {
                        viewAllManagers();
                    } else if (action.equals("Logout")) {
                        cardLayout.show(cardPanel, "AdminLogin");
                    }
                     else {
                        // Placeholder: Replace with actual functionality
                        JOptionPane.showMessageDialog(adminActionsPanel, action + " clicked!", "Action", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            // Add button to the panel
            adminActionsPanel.add(actionButton);
            adminActionsPanel.add(Box.createVerticalStrut(10)); // Spacing between buttons
        }

        // Add admin actions panel to the card panel with the name "AdminActions"
        cardPanel.add(adminActionsPanel, "AdminActions");
    }

    public void addManager(){
        JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField phoneField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    formPanel.add(new JLabel("Full Name:"));
    formPanel.add(nameField);
    formPanel.add(new JLabel("Email:"));
    formPanel.add(emailField);
    formPanel.add(new JLabel("Phone:"));
    formPanel.add(phoneField);
    formPanel.add(new JLabel("Password:"));
    formPanel.add(passwordField);

    int result = JOptionPane.showConfirmDialog(null, formPanel, "Enter Manager Details", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        String fullName = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());

        Manager mg = new Manager();
        try {
            mg.addManager(fullName, email, password, phone);  
            JOptionPane.showMessageDialog(null, "Manager added successfully.");
        } catch (InvalidEmailFormatException | InvalidPhoneNumberException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    }

    public void deleteManager() {
        String userId = JOptionPane.showInputDialog("Enter Manager's User ID to delete:");
    
        Manager mg2 = new Manager();
        try {
            mg2.deleteManager(userId);
            JOptionPane.showMessageDialog(null, "Manager deleted successfully.");
        } catch (ManagerNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void updateManagerDetails() {
        String userId = JOptionPane.showInputDialog("Enter Manager's User ID to update:");
    
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    
        JTextField newNameField = new JTextField();
        JTextField newEmailField = new JTextField();
        JTextField newPhoneField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
    
        formPanel.add(new JLabel("New Full Name:"));
        formPanel.add(newNameField);
        formPanel.add(new JLabel("New Email:"));
        formPanel.add(newEmailField);
        formPanel.add(new JLabel("New Phone:"));
        formPanel.add(newPhoneField);
        formPanel.add(new JLabel("New Password:"));
        formPanel.add(newPasswordField);
    
        int result = JOptionPane.showConfirmDialog(null, formPanel, "Update Manager Details", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            String newName = newNameField.getText();
            String newEmail = newEmailField.getText();
            String newPhone = newPhoneField.getText();
            String newPassword = new String(newPasswordField.getPassword());
    
            Manager mg3 = new Manager();
            try {
                mg3.updateManager(userId, newName, newEmail, newPassword, newPhone);
                JOptionPane.showMessageDialog(null, "Manager details updated successfully.");
            } catch (ManagerNotFoundException | InvalidEmailFormatException | InvalidPhoneNumberException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
    

    public void viewAllManagers() {
        Manager mg4 = new Manager();
        List<Manager> managers = mg4.viewAllManagers(); // Assuming getUserList() fetches all managers
        
        if (managers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No managers found.");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Manager manager : managers) {
                builder.append(manager.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(builder.toString());
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "List of Managers", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
