import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

public class CustomerDataFile extends UserDataFile<Customer> implements Serializable{

    public CustomerDataFile(String filePath) {
        super(filePath);
    }
    public CustomerDataFile() {
        super("");
    }

    @Override
    public Customer createUserFromData(String[] data) {
        if (data.length == 5) {
            return new Customer(data[0], data[1], data[2], data[3], data[4]);
        }
        return null;
    }

    

    @Override
    public String generateNewUserId() {
        loadSerializedData();
        return "CUST" + (lastUserId + 1);
    }

    @Override
    public String userToString(Customer user) {
        return user.getUserId() + "," + super.toString(user); 
    }

    // Delete Customer by ID, using inherited deleteUserInFile
    public void deleteCustomer(String customerId) {
        deleteUserInFile(customerId); 
    }

    @Override
    public Customer createNewUser(String userId, String fullName, String email, String password, String phone) {
        return new Customer(userId, fullName, email, password, phone);
    }

    public void saveUserList(Collection<Customer> customers) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("CustomerData.ser"))) {
        for (Customer customer : customers) {
            writer.write(customer.toString());  // Ensure to format customer data properly
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error saving customer data: " + e.getMessage());
    }
}
}
