import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class AdminActionsGui {
    private JPanel adminActionsPanel = new JPanel();
    CarInventory carInventory = new CarInventory();
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JPanel cardPanel;
    private CardLayout cardLayout;

      private Admin admin;


    public AdminActionsGui(CardLayout cardLayout, JPanel cardPanel) {
        adminActionsPanel = new JPanel();
        adminActionsPanel.setLayout(new BorderLayout());

        
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setPreferredSize(new Dimension(800,60));

        
        JLabel headingLabel = new JLabel("McRental", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 35));
        headingLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 20));
        headerPanel.add(headingLabel, BorderLayout.CENTER);

        JLabel subHeadingLabel = new JLabel("Admin", JLabel.RIGHT);
        subHeadingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subHeadingLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 20));
        headerPanel.add(subHeadingLabel, BorderLayout.EAST);

        adminActionsPanel.add(headerPanel, BorderLayout.NORTH);

        // Left navigation panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.DARK_GRAY);
        leftPanel.setPreferredSize(new Dimension(200, 600));

        String[] actions = {
                "Add a Manager", "Delete a Manager", "Update Manager Details", "View All Managers",
                "Add a Car", "Delete a Car", "Update Car Details", "View All Cars","Logout"
        };

        for (String action : actions) {
            JButton actionButton = new JButton(action);
            actionButton.setFont(new Font("Arial", Font.PLAIN, 13));
            actionButton.setForeground(Color.WHITE);
            actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            actionButton.setMaximumSize(new Dimension(180, 40));
            actionButton.setBackground(Color.DARK_GRAY);

            actionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateRightPanel(action);
                }
            });

            leftPanel.add(Box.createVerticalStrut(10)); // Spacing
            leftPanel.add(actionButton);
        }

        adminActionsPanel.add(leftPanel, BorderLayout.WEST);

        // Right content panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);


        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.GRAY);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming "Main" is the card name for the login or main page
                cardLayout.show(cardPanel, "Main");
            }
        });

        JLabel defaultLabel = new JLabel("Select an option from the left panel", JLabel.CENTER);
        defaultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        rightPanel.add(defaultLabel, BorderLayout.CENTER);

        adminActionsPanel.add(rightPanel, BorderLayout.CENTER);

        // Add adminActionsPanel to cardPanel
        cardPanel.add(adminActionsPanel, "AdminActions");

       
    }

    


    private void updateRightPanel(String action) {
        rightPanel.removeAll();

        switch (action) {
            
            case "View Profile":
                // Link to View Profile method
                viewAdminProfile(admin);
                break;

            case "Edit Profile":
                // Link to Edit Profile method
                editAdminProfile(admin);
                break;

            case "Add a Manager":
                addManager();
                break;

            case "Delete a Manager":
                deleteManager();
                break;

            case "Update Manager Details":
                updateManagerDetails();
                break;

            case "View All Managers":
                viewAllManagers();
                break;

                case "Add a Car":
                addCar();
                break;

            case "Delete a Car":
                deleteCar();
                break;

            case "Update Car Details":
                updateCarDetails();
                break;

            case "View All Cars":
                viewAllCars();
                break;
            case "Logout":
                cardLayout.show(cardPanel, "Main");
                break;

            default:
                JLabel label = new JLabel("Select " + action, JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                rightPanel.add(label, BorderLayout.CENTER);
        }

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void setPlaceholderTextAndFocusListener(final JTextField textField, final String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY); // Placeholder color

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // User text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY); // Placeholder color again
                    textField.setText(placeholder);
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel cardPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

        new AdminActionsGui(cardLayout, cardPanel);

        frame.add(cardPanel);
        frame.setVisible(true);
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

        private void addCar() {
            JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

    JTextField modelField = new JTextField();
    JTextField rentField = new JTextField();
    JTextField colorField = new JTextField();
    JTextField regNumField = new JTextField();
    JTextField seatingCapacityField = new JTextField();
    JTextField engineTypeField = new JTextField();
    JTextField transmissionField = new JTextField();
    JTextField insuranceField = new JTextField();

    // Set placeholder text for the fields
    setPlaceholderTextAndFocusListener(modelField, "Enter car model");
    setPlaceholderTextAndFocusListener(rentField, "Enter rent per day");
    setPlaceholderTextAndFocusListener(colorField, "Enter car color");
    setPlaceholderTextAndFocusListener(regNumField, "Enter registration number");
    setPlaceholderTextAndFocusListener(seatingCapacityField, "Enter seating capacity");
    setPlaceholderTextAndFocusListener(engineTypeField, "Enter engine type (e.g., Petrol, Diesel, Electric, etc.)");
    setPlaceholderTextAndFocusListener(transmissionField, "Enter transmission type (automatic/manual)");
    setPlaceholderTextAndFocusListener(insuranceField, "Enter insurance percentage");

    formPanel.add(new JLabel("Car Model:"));
    formPanel.add(modelField);
    formPanel.add(new JLabel("Rent Per Day:"));
    formPanel.add(rentField);
    formPanel.add(new JLabel("Car Color:"));
    formPanel.add(colorField);
    formPanel.add(new JLabel("Registration Number:"));
    formPanel.add(regNumField);
    formPanel.add(new JLabel("Seating Capacity:"));
    formPanel.add(seatingCapacityField);
    formPanel.add(new JLabel("Engine Type:"));
    formPanel.add(engineTypeField);
    formPanel.add(new JLabel("Transmission Type:"));
    formPanel.add(transmissionField);
    formPanel.add(new JLabel("Insurance Percent:"));
    formPanel.add(insuranceField);

    int result = JOptionPane.showConfirmDialog(null, formPanel, "Enter Car Details", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        // Gather input values
        String model = modelField.getText();
        double rent = Double.parseDouble(rentField.getText());
        String color = colorField.getText();
        String regNum = regNumField.getText();
        int seatingCapacity = Integer.parseInt(seatingCapacityField.getText());
        String engineType = engineTypeField.getText();
        String transmission = transmissionField.getText();
        double insurance = Double.parseDouble(insuranceField.getText());

        // Use engineType to determine the car category
        String category = Car.determineCarCategory(engineType);

        // Validate that the engineType is correct
        if (category.equals("Uncategorized")) {
            JOptionPane.showMessageDialog(null, "Invalid Engine Type. Unable to determine car category.");
            return;
        }

        // Create Car Object based on determined category
        Car newCar;
        switch (category.toLowerCase()) {
            case "economy":
                newCar = new EconomyCar(model, true, rent, color, regNum, seatingCapacity, engineType, transmission, seatingCapacity);
                break;
            case "luxury":
                newCar = new LuxuryCar(model, true, rent, color, regNum, seatingCapacity, engineType, transmission, category);
                break;
            case "electric":
                newCar = new ElectricCar(model, true, rent, color, regNum, seatingCapacity, engineType, transmission, insurance, insurance, insurance);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid Car Category.");
                return;
        }

        // Assuming we have an addCar method in the Admin class
        Admin admin = new Admin();
        admin.addCar(newCar);
        JOptionPane.showMessageDialog(null, "Car added successfully!");
    }
        }

        
        
        private void updateCarDetails() {
            String carId = JOptionPane.showInputDialog("Enter Car ID to update:");

            if (carId == null || carId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Car ID cannot be empty.");
                return;
            }

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
            JTextField modelField = new JTextField();
            JTextField rentField = new JTextField();
            JTextField colorField = new JTextField();
            JTextField seatingCapacityField = new JTextField();
            JTextField engineTypeField = new JTextField();
            JTextField transmissionField = new JTextField();
            JTextField insuranceField = new JTextField();
            JTextField categoryField = new JTextField();
            JTextField availabilityField = new JTextField();

             // Set placeholder text
             setPlaceholderTextAndFocusListener(modelField, "Enter new car model");
        setPlaceholderTextAndFocusListener(rentField, "Enter new rent per day");
        setPlaceholderTextAndFocusListener(colorField, "Enter new car color");
        setPlaceholderTextAndFocusListener(seatingCapacityField, "Enter new seating capacity");
        setPlaceholderTextAndFocusListener(engineTypeField, "Enter new engine type (e.g., Petrol, Diesel)");
        setPlaceholderTextAndFocusListener(transmissionField, "Enter new transmission type (automatic/manual)");
        setPlaceholderTextAndFocusListener(insuranceField, "Enter new insurance percentage");
        setPlaceholderTextAndFocusListener(categoryField, "Enter new car category (Economy/Luxury/Electric)");
        setPlaceholderTextAndFocusListener(availabilityField, "Enter new availability status (true/false)");
        
            formPanel.add(new JLabel("New Car Model:"));
            formPanel.add(modelField);
            formPanel.add(new JLabel("New Rent Per Day:"));
            formPanel.add(rentField);
            formPanel.add(new JLabel("New Car Color:"));
            formPanel.add(colorField);
            formPanel.add(new JLabel("New Seating Capacity:"));
            formPanel.add(seatingCapacityField);
            formPanel.add(new JLabel("New Engine Type:"));
            formPanel.add(engineTypeField);
            formPanel.add(new JLabel("New Transmission Type:"));
            formPanel.add(transmissionField);
            formPanel.add(new JLabel("New Insurance Percent:"));
            formPanel.add(insuranceField);
            formPanel.add(new JLabel("New Car Category:"));
            formPanel.add(categoryField);
            formPanel.add(new JLabel("New Availability Status (true/false):"));
            formPanel.add(availabilityField);
        
            int result = JOptionPane.showConfirmDialog(null, formPanel, "Update Car Details", JOptionPane.OK_CANCEL_OPTION);
        
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String model = modelField.getText();
            double rent = Double.parseDouble(rentField.getText());
            String color = colorField.getText();
            int seatingCapacity = Integer.parseInt(seatingCapacityField.getText());
            String engineType = engineTypeField.getText();
            String transmission = transmissionField.getText();
            double insurance = Double.parseDouble(insuranceField.getText());
            String category = categoryField.getText();
            boolean availability = Boolean.parseBoolean(availabilityField.getText());  // true/false value for availability

            // Validate car category
            if (!category.equalsIgnoreCase("economy") && !category.equalsIgnoreCase("luxury") && !category.equalsIgnoreCase("electric")) {
                JOptionPane.showMessageDialog(null, "Invalid Car Category.");
                return;
            }

            // Assuming we have a method in Admin to update the car
            Admin admin = new Admin();
            admin.updateCar(carId, category, model, availability, rent, color, carId, seatingCapacity, engineType, transmission, insurance);

            JOptionPane.showMessageDialog(null, "Car details updated successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Rent, Insurance, and Seating Capacity.");
        }
                }
            }
        

        private void viewAllCars() {
            // Get the list of all available cars
            Admin admin = new Admin();
            List<Car> cars = admin.viewAllCars(admin.carInventory); // Assuming getAllCars fetches all cars
            String[] columnNames = {"Car ID", "Model", "Category", "Rent Per Day", "Color", "Seating Capacity", "Engine Type", "Transmission"};
            
            // Prepare data for JTable
            Object[][] data = new Object[cars.size()][columnNames.length];
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                data[i][0] = car.getCarId(); // Assuming getCarId() returns car ID
                data[i][1] = car.getCarModel(); // Assuming getCarModel() returns model
                data[i][2] = car.getCarCategory(); // Assuming getCategory() returns category
                data[i][3] = car.getRentPerDay(); // Assuming getRentPerDay() returns rent per day
                data[i][4] = car.getColor(); // Assuming getColor() returns color
                data[i][5] = car.getSeatingCapacity(); // Assuming getSeatingCapacity() returns seating capacity
                data[i][6] = car.getEngineType(); // Assuming getEngineType() returns engine type
                data[i][7] = car.getTransmision(); // Assuming getTransmission() returns transmission type
            }
        
            // Create the JTable
            JTable table = new JTable(data, columnNames);
            table.setFillsViewportHeight(true);
            
            // Display the table in a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scrollPane, "All Cars", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        private void deleteCar() {
            // Ask the user for the Car ID
            String carId = JOptionPane.showInputDialog("Enter Car ID to delete:");
        
            if (carId != null && !carId.trim().isEmpty()) {
                Admin admin = new Admin();
                
                // Attempt to delete the car
                boolean success = admin.deleteCar(carId); // Assuming deleteCar returns a boolean indicating success
                if (success) {
                    JOptionPane.showMessageDialog(null, "Car deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Car not found or deletion failed.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid Car ID.");
            }
        }
        
        private void viewAdminProfile(Admin admin) {
            JPanel profilePanel = new JPanel();
            profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        
            JLabel label1 = new JLabel("Admin ID: " + admin.getUserId());
            JLabel label2 = new JLabel("Full Name: " + admin.getFullName());
            JLabel label3 = new JLabel("Email: " + admin.getEmail());
            JLabel label4 = new JLabel("Phone Number: " + admin.getPhoneNumber());
        
            profilePanel.add(label1);
            profilePanel.add(label2);
            profilePanel.add(label3);
            profilePanel.add(label4);
        
            rightPanel.add(profilePanel, BorderLayout.CENTER);
        }
        
    private void editAdminProfile(Admin admin) {
            // Panel for editing the profile
    JPanel editProfilePanel = new JPanel();
    editProfilePanel.setLayout(new BoxLayout(editProfilePanel, BoxLayout.Y_AXIS));

    // Create text fields for each editable attribute
    JTextField fullNameField = new JTextField(admin.getFullName());
    JTextField emailField = new JTextField(admin.getEmail());
    JTextField phoneField = new JTextField(admin.getPhoneNumber());
    JPasswordField passwordField = new JPasswordField(20);

    // Create a button to save the updated information
    JButton saveButton = new JButton("Save Changes");
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newName = fullNameField.getText();
            String newEmail = emailField.getText();
            String newPhone = phoneField.getText();
            String newPassword = new String(passwordField.getPassword());

            try {
                // Attempt to edit the admin profile
                admin.editAdminProfile(newName, newEmail, newPassword, newPhone);
                
                // Show success message
                JLabel successLabel = new JLabel("Profile updated successfully!", JLabel.CENTER);
                successLabel.setFont(new Font("Arial", Font.BOLD, 16));
                editProfilePanel.add(successLabel);
            } catch (InvalidEmailException | EmptyFieldException ex) {
                // Show error message if validation fails
                JLabel errorLabel = new JLabel(ex.getMessage(), JLabel.CENTER);
                errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                errorLabel.setForeground(Color.RED);
                editProfilePanel.add(errorLabel);
            }

            // Revalidate and repaint the panel to show updated content or errors
            rightPanel.revalidate();
            rightPanel.repaint();
        }
    });

    // Add components to the editProfilePanel
    editProfilePanel.add(new JLabel("Edit Full Name:"));
    editProfilePanel.add(fullNameField);
    editProfilePanel.add(new JLabel("Edit Email:"));
    editProfilePanel.add(emailField);
    editProfilePanel.add(new JLabel("Edit Phone Number:"));
    editProfilePanel.add(phoneField);
    editProfilePanel.add(new JLabel("Edit Password:"));
    editProfilePanel.add(passwordField);
    editProfilePanel.add(saveButton);

    // Add the edit profile panel to the right panel
    rightPanel.removeAll();
    rightPanel.add(editProfilePanel, BorderLayout.CENTER);
    rightPanel.revalidate();
    rightPanel.repaint();
        }
      

}

  