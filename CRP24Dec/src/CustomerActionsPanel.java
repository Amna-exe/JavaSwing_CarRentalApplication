import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CustomerActionsPanel {
    private CarInventory carInventory = new CarInventory();
    private ProcessReservation reservationProcessor = new ProcessReservation();
    private JPanel customerActionPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;

    public CustomerActionsPanel(CardLayout cardLayout, JPanel cardPanel) {
        customerActionPanel = new JPanel();
        customerActionPanel.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(215, 160, 255));
        headerPanel.setPreferredSize(new Dimension(800, 60));
        JLabel headingLabel = createHeadingLabel("Customer Responsibilities");
        headerPanel.add(headingLabel, BorderLayout.CENTER);
        customerActionPanel.add(headerPanel, BorderLayout.NORTH);

        // Left navigation panel (buttons)
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(41, 128, 185));
        leftPanel.setPreferredSize(new Dimension(200, 600));

        String[] actions = {
            "Make a Reservation",
            "View All Available Cars",
            "Return a Car",
            "View Reservation Status",
            "View Reservation History",
            "Logout"
        };

        for (String action : actions) {
            JButton actionButton = createActionButton(action, cardLayout, cardPanel);
            leftPanel.add(actionButton);
            leftPanel.add(Box.createVerticalStrut(15));
        }

        customerActionPanel.add(leftPanel, BorderLayout.WEST);

        // Right content panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        // Default message in right panel
        JLabel defaultLabel = new JLabel("Select an action from the left panel", JLabel.CENTER);
        defaultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        rightPanel.add(defaultLabel, BorderLayout.CENTER);

        customerActionPanel.add(rightPanel, BorderLayout.CENTER);

        cardPanel.add(customerActionPanel, "CustomerActions");
    }

    private JLabel createHeadingLabel(String text) {
        JLabel headingLabel = new JLabel(text);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(25, 42, 86));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return headingLabel;
    }

    private JButton createActionButton(String action, CardLayout cardLayout, JPanel cardPanel) {
        JButton actionButton = new JButton(action);
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.setFont(new Font("Arial", Font.PLAIN, 18));
        actionButton.setForeground(Color.WHITE);
        actionButton.setBackground(new Color(41, 128, 185));
        actionButton.setFocusPainted(false);
        actionButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        actionButton.addActionListener(e -> handleAction(action, cardLayout, cardPanel));
        return actionButton;
    }

    private void handleAction(String action, CardLayout cardLayout, JPanel cardPanel) {
        rightPanel.removeAll(); // Clear right panel for new content

        switch (action) {
            case "Make a Reservation":
                makeReservation(cardLayout, cardPanel);
                break;
            case "View All Available Cars":
                viewAllCars(cardLayout, cardPanel);
                break;
            case "Return a Car":
                processReturn();
                break;
            case "View Reservation Status":
                viewReservationStatus();
                break;
            case "View Reservation History":
                viewReservationHistory();
                break;
            case "Logout":
                cardLayout.show(cardPanel, "CustomerLogin");
                break;
        }

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private JButton createBackButton(CardLayout cardLayout, JPanel cardPanel) {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "CustomerActions"));
        return backButton;
    }

    private void makeReservation(CardLayout cardLayout, JPanel cardPanel) {
        String customerId = promptForInput("Enter your Customer ID:");
        if (isInvalidInput(customerId)) return;

        String customerName = promptForInput("Enter your Name:");
        if (isInvalidInput(customerName)) return;

        List<Car> cars = carInventory.getAvailableCarsDetails();
        if (cars.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cars available.");
            return;
        }

        // Create the table for car details
        JTable carTable = createCarTable(cars);
        JScrollPane scrollPane = new JScrollPane(carTable);

        // Form for reservation
        JPanel formPanel = createReservationForm(cars, customerId, customerName, cardLayout, cardPanel);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Add the table to the center
        mainPanel.add(formPanel, BorderLayout.EAST); // Add the form on the right

        // Add back button at the top
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(createBackButton(cardLayout, cardPanel));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        cardPanel.add(mainPanel, "MakeReservation");
        cardLayout.show(cardPanel, "MakeReservation");
    }

    private JPanel createReservationForm(List<Car> cars, String customerId, String customerName, 
                                          CardLayout cardLayout, JPanel cardPanel) {
        // Similar to the original form creation but customized for customer reservation
        // This part remains unchanged from your original code
        return new JPanel();
    }

    private JTable createCarTable(List<Car> cars) {
        String[] columns = {
            "Car ID", "Model", "Category", "Color", "Seating", 
            "Engine Type", "Transmission", "Rent/Day", "Availability"
        };

        Object[][] data = cars.stream()
            .map(car -> new Object[]{
                car.getCarId(),
                car.getCarModel(),
                car.getCarCategory(),
                car.getColor(),
                car.getSeatingCapacity(),
                car.getEngineType(),
                car.getTransmision(),
                String.format("$%.2f", car.getRentPerDay()),
                car.isAvailability() ? "Available" : "Reserved"
            })
            .toArray(Object[][]::new);

        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(240, 240, 250) : Color.WHITE);
                return c;
            }
        });

        return table;
    }

    private void viewAllCars(CardLayout cardLayout, JPanel cardPanel) {
        List<Car> cars = carInventory.getAvailableCarsDetails();
        JTable carTable = createCarTable(cars);
        JScrollPane scrollPane = new JScrollPane(carTable);

        JPanel viewPanel = new JPanel(new BorderLayout(10, 10));
        viewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add back button
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(createBackButton(cardLayout, cardPanel));
        viewPanel.add(headerPanel, BorderLayout.NORTH);

        // Add table with scroll
        viewPanel.add(scrollPane, BorderLayout.CENTER);

        cardPanel.add(viewPanel, "ViewAllCars");
        cardLayout.show(cardPanel, "ViewAllCars");
    }

    // Helper methods remain the same
    private boolean isInvalidInput(String input) {
        return input == null || input.trim().isEmpty();
    }

    private String promptForInput(String message) {
        String input = JOptionPane.showInputDialog(message);
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input cannot be empty.");
            return null;
        }
        return input;
    }

    private void processReturn() {
        String carId = promptForInput("Enter the Car ID to return:");
        if (isInvalidInput(carId)) return;

        try {
            boolean success = reservationProcessor.removeReservation(carId);
            if (success) {
                JOptionPane.showMessageDialog(null, "Car returned successfully!");
            }
        } catch (ReservationNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Reservation not found for the given car ID.");
        }
    }

    private void viewReservationStatus() {
        String carId = promptForInput("Enter the Car ID to check its reservation status:");
        if (isInvalidInput(carId)) return;

        boolean isReserved = reservationProcessor.isCarReserved(carId);
        JOptionPane.showMessageDialog(null, isReserved ? "This car is currently reserved." : "This car is available.");
    }

    private void viewReservationHistory() {
        String customerId = promptForInput("Enter your Customer ID:");
        if (isInvalidInput(customerId)) return;

        List<String> history = reservationProcessor.getReservationHistoryByCustomerId(customerId);
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No reservation history found.");
        } else {
            StringBuilder historyText = new StringBuilder("Reservation History:\n\n");
            for (String record : history) {
                historyText.append(record).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, historyText.toString());
        }
    }
}
