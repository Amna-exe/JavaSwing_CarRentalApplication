

import java.util.HashMap;
import java.util.List;

final class CustomerLogin {
    private final CustomerDataFile customerDetails;
    private final HashMap<String, Customer> customerLoginMap;

    // Constructor
    public CustomerLogin() {
        this.customerDetails = new CustomerDataFile("CustomerData.txt"); 
        this.customerLoginMap = new HashMap<>();
        loadCustomerData(); 
    }

    // Load Customer Data 
    public void loadCustomerData() {
        
        if (customerDetails.getUserList() != null) {
            for (Customer customer : customerDetails.getUserList()) {
                customerLoginMap.put(customer.getUserId(), customer);
            }
        }
    }

    public Customer getCustomer(String customerId) {
        return customerLoginMap.get(customerId); 
    }

    public String getCustomerIdFromName(String name){

        List<Customer> customers = customerDetails.getUserList();
        if (customers.isEmpty()) {
            System.out.println("No customer found.");
        } else {
            for (Customer customer : customers) {
                if (customer.getFullName().equals(name)){
                    return customer.getUserId();
                }
            }
        }
        return null;

    }

    // Get the Map of Customer IDs and Customer passwords
    public HashMap<String, Customer> getCustomerLoginMap() {
        return customerLoginMap;
    }

    // Validate the Login (Customer ID and Password)
    public boolean validateLogin(String customerId, String password) {
        loadCustomerData();
        Customer cust = customerLoginMap.get(customerId);
        return cust != null && cust.getPassword().equals(password); 
    }
    
    public void saveCustomerData() {
        customerDetails.saveUserList(customerLoginMap.values()); // Save the entire map of customers to the file
    }
}
