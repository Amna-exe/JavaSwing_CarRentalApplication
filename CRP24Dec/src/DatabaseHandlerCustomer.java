import java.util.ArrayList;


//added with the intial plan to listen to all the action listeneres
//didn't work quite well with anything.
//still here, will delete letter

// ^_^

public class DatabaseHandlerCustomer {
    private static ArrayList<String> managers = new ArrayList<>();
    private static ArrayList<String> customers = new ArrayList<>();

    public static void addManager(String managerName) {
        managers.add(managerName);
    }

    public static String getAllCustomers() {
        if (customers.isEmpty()) {
            return "No customers found.";
        }
        StringBuilder customerList = new StringBuilder("Customer List:\n");
        for (String customer : customers) {
            customerList.append("- ").append(customer).append("\n");
        }
        return customerList.toString();
    }

}
