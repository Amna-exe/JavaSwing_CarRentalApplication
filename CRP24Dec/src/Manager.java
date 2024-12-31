
import java.util.Collections;
import java.util.List;

public class Manager extends User {

    private ManagerDataFile managerLoginData;  // Instance variable (no need for static)
    private CarInventory carInventory;
    private String managerId;
    

    // Constructor for Manager class
    public Manager(String managerId, String fullName, String email, String password, String phoneNumber) {
        super(fullName, email, password, phoneNumber);  // Calling the parent class constructor
        this.managerId = managerId;
        this.managerLoginData = new ManagerDataFile("ManagerData.txt");
        this.carInventory = new CarInventory();
    }

    // Default constructor (if needed, but should avoid initializing managerLoginData here)
    public Manager() {
        super(null, null, null, null);  // Calling the parent class constructor with nulls
        this.managerId = null;
        this.managerLoginData = new ManagerDataFile("ManagerData.txt");
        this.carInventory = new CarInventory();
    }

    @Override
    public String getUserId() {
        return managerId;  // Returns manager's unique ID
    }

    // Getter and setter
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return getUserId() + "," + super.toString();  
    }

    // View manager profile
    public void viewManagerProfile() {
        System.out.println("Manager ID: " + managerId);
        System.out.println("Full Name: " + getFullName());
        System.out.println("Email: " + getEmail());
        System.out.println("Email: " + getPassword());
        System.out.println("Phone Number: " + getPhoneNumber());
    }

    // Add a new manager
    public void addManager(String fullName, String email, String password, String phone) throws InvalidEmailFormatException, InvalidPhoneNumberException {

        // Validate email format
        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException("Invalid email format.");
        }

        // Validate phone number format
        if (!isValidPhoneNumber(phone)) {
            throw new InvalidPhoneNumberException("Invalid phone number format.");
        }

        String mgId = managerLoginData.generateNewUserId();
        Manager newManager = new Manager(mgId, fullName, email, password, phone);
        managerLoginData.addUser(newManager);  // Add new manager to the file
    }


    // Update existing manager's details
    public void updateManager(String userId, String newName, String newEmail, String newPassword, String newPhone) throws ManagerNotFoundException, InvalidEmailFormatException, InvalidPhoneNumberException {
        // Check if manager exists
        Manager existingManager = managerLoginData.getManagerById(userId);
        if (existingManager == null) {
            throw new ManagerNotFoundException("Manager with ID " + userId + " not found.");
        }

        // Validate email format
        if (!isValidEmail(newEmail)) {
            throw new InvalidEmailFormatException("Invalid email format.");
        }

        // Validate phone number
        if (!isValidPhoneNumber(newPhone)) {
            throw new InvalidPhoneNumberException("Invalid phone number format.");
        }

        // Update manager details in the data
        managerLoginData.updateUserInFile(userId, newName, newEmail, newPassword, newPhone);
    }

    // Delete a manager
    public void deleteManager(String userId) throws ManagerNotFoundException {
        boolean managerFound = managerLoginData.deleteUserInFile(userId);
        if (!managerFound) {
            throw new ManagerNotFoundException("Manager with ID " + userId + " not found.");
        }
    }

    // View all managers
    //changed it so that it returns managerslist (*_*)
    public List<Manager> viewAllManagers() {
        List<Manager> managers = managerLoginData.getUserList();
        if (managers.isEmpty()) {
            return Collections.emptyList();
        } else {
            return managers;
        }
    }

    // Validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    // Validate phone number format (must be 10 digits)
    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{11}");
    }

        // Method to view all cars in inventory
        protected void viewCarsByCategory(String carCategory) {
            carInventory.filterCarsByType(carCategory);
        }

        protected void viewAllCars(CarInventory carInventory){
            carInventory.getAvailableCarsDetails();
        }
}
