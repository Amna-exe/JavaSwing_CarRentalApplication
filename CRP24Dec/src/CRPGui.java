import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRPGui {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Role buttons
    private JButton customerButton;
    private JButton adminButton;
    private JButton managerButton;

    // Panels for different screens
    private JPanel mainPanel;
    private JPanel customerPanel;
    private JPanel managerPanel;

    public CRPGui() {
        // Initialize the frame and CardLayout
        frame = new JFrame("McRental - Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //add main screen
        JPanel mainPanel = createMainScreen();
        cardPanel.add(mainPanel,"Main");
        // Set the frame visible

          
  
          managerPanel = createManagerScreen();
          cardPanel.add(managerPanel, "Manager");
  
        
  

        frame.setContentPane(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createMainScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Welcome to McRental!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        customerButton = new JButton("Customer Interface");
        adminButton = new JButton("Admin Interface");
        managerButton = new JButton("Manager Interface");

        customerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        managerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add ActionListeners to buttons to switch between screens
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCustomerInterface();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAdminInterface();  // Dynamically load the Admin interface
            }
        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadManagerInterface();
            }
        });

        panel.add(Box.createVerticalStrut(20));
        panel.add(headingLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(customerButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(adminButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(managerButton);

        return panel;
    }

    private JPanel createCustomerScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Welcome to the Customer Screen");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Customer Login ");
        JButton signupButton = new JButton("Customer Sign Up");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back to Main");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the login method
                new CustomerLogin(); // You can call your customer login method here
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the signup method
                new CustomerSignup(); // You can call your signup method here
            }
        });

        panel.add(label);
        panel.add(Box.createVerticalStrut(20));  // Space between label and buttons
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(10));  // Space between buttons
        panel.add(signupButton);
        panel.add(Box.createVerticalStrut(10));  // Space between buttons
        panel.add(backButton);

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

    private void loadManagerInterface(){
        ManagerInterface managerInterface = new ManagerInterface(cardLayout, cardPanel);
        cardLayout.show(cardPanel,"ManagerLogin");
    }
    private JPanel createManagerScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Welcome to the Manager Screen");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back to Main");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main");
            }
        });

        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(backButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CRPGui(); // Launch the GUI
            }
        });
    }
}
