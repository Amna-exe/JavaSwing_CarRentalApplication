import java.util.List;

public class Customer extends User {

    
    private CustomerDataFile customerLoginData; 
    private String customerId;
    

    // Constructor for Manager class
    public Customer(String customerId, String fullName, String email, String password, String phoneNumber) {
        super(fullName, email, password, phoneNumber); 
        this.customerId = customerId;
        this.customerLoginData = new CustomerDataFile("CustomerData.txt");
    }

    //a constructor without the id whre the id is genereatd

    public Customer(String fullName,String email,String password,String phoneNumber){
        super(fullName, email, password, phoneNumber);
        this.customerId = getUserId();
        this.customerLoginData = new CustomerDataFile("CustomerData.txt");
    }
    // Default constructor 
    public Customer() {
        super(null, null, null, null);  
        this.customerId = null;
        this.customerLoginData = new CustomerDataFile("CustomerData.txt");
    }

    public Customer(String customerId, String customerName) {
        this.customerLoginData = new CustomerDataFile("CustomerData.txt");
        this.customerId = customerId;
        this.fullName = customerName;
    }

    @Override
    public String getUserId() {
        return customerId;  
    }


    // Getter and setter
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return getUserId() + "," + super.toString();  
    }

    // View customer profile
    public void viewCustomerProfile() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Full Name: " + getFullName());
        System.out.println("Email: " + getEmail());
        System.out.println("Email: " + getPassword());
        System.out.println("Phone Number: " + getPhoneNumber());
    }

    // Add a new customer
    public void addCustomer(String fullName, String email, String password, String phone) {
        String custId = customerLoginData.generateNewUserId();
        Customer newCustomer = new Customer(custId, fullName, email, password, phone);
        customerLoginData.addUser(newCustomer); 
    }


    // Update existing customer's details
    public void updateCustomer(String userId, String newName, String newEmail, String newPassword, String newPhone) throws CustomerNotFoundException {

        boolean customerFound = customerLoginData.updateUserInFile(userId, newName, newEmail, newPassword, newPhone);

        if (!customerFound) {
            throw new CustomerNotFoundException("Customer with ID " + userId + " not found.");
        }
    }

    // Delete a customer
    protected void deleteCustomer(String userId) throws CustomerNotFoundException {
        // Now this will return a boolean indicating if the customer was found and deleted
        boolean customerFound = customerLoginData.deleteUserInFile(userId);
    
        if (!customerFound) {
            throw new CustomerNotFoundException("Customer with ID " + userId + " not found.");
        }
    }
    

    // View all customers
    public void viewAllCustomers() {
        List<Customer> customers = customerLoginData.getUserList();
        if (customers.isEmpty()) {
            System.out.println("No customer found.");
        } else {
            System.out.println("List of Managers:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}
