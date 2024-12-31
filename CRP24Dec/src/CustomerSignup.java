import java.util.regex.Pattern;

public class CustomerSignup {

    CustomerLogin customerLog = new CustomerLogin();
    CustomerDataFile customerDetails = new CustomerDataFile("customerData.txt");

    // Check if the email is already registered
    protected boolean isEmailRegistered(String email) throws AlreadyRegisteredEmailException {
        if (customerLog.getCustomerLoginMap() != null && customerLog.getCustomerLoginMap().containsKey(email)) {
            throw new AlreadyRegisteredEmailException("Email is already registered.");
        }
        return false;
    }

    // Validate customer details (email, password, phone, and full name)
    protected boolean validateCustomerDetails(String fullName, String email, String password, String phone) throws InvalidEmailFormatException, EmptyFieldException, InvalidPasswordException, InvalidPhoneNumberException {
        if (fullName.isEmpty()) {
            throw new EmptyFieldException("Full name cannot be empty.");
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException("Invalid email format. Please enter a valid email.");
        }

        if (password.length() < 6 || password.length() > 12) {
            throw new InvalidPasswordException("Password must be between 6-12 characters.");
        }

        if (!phone.matches("\\d{11}")) {
            throw new InvalidPhoneNumberException("Phone number must be exactly 11 digits.");
        }
        return true;
    }

    // Register a new customer after validating their details
    protected Customer registerNewCustomer(String fullName, String email, String password, String phoneNumber) throws AlreadyRegisteredEmailException, InvalidEmailFormatException, EmptyFieldException, InvalidPasswordException, InvalidPhoneNumberException {
        customerLog.loadCustomerData();  // Ensure customer data is loaded before checking

        // Check if the email is already registered
        isEmailRegistered(email);

        // Generate a new user ID for the customer
        String customerId = customerDetails.generateNewUserId();

        // Create a new Customer object
        Customer newCustomer = new Customer(customerId, fullName, email, password, phoneNumber);

        // Add the new customer to the list and save to file
        customerDetails.getUserList().add(newCustomer);  // Save the new customer to the file
        customerLog.getCustomerLoginMap().put(email, newCustomer);  // Add the Customer object to the login map
        customerDetails.addUser(newCustomer);  // Save the new customer to the file
        System.out.println("New customer registered successfully.");
        return newCustomer;  // Return true when registration is successful
    }

    // Validate email format
    protected boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";  // Updated regex
        return Pattern.matches(emailRegex, email);
    }

    // Validate password (6-12 characters)
    protected boolean isValidPassword(String password) {
        return password.length() >= 6 && password.length() <= 12;
    }

    // Validate phone number (11 digits)
    protected boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{11}");  // Assuming exactly 11 digits
    }
}
