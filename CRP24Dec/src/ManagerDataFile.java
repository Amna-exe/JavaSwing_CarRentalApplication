import java.io.Serializable;
import java.util.List;

public class ManagerDataFile extends UserDataFile<Manager> implements Serializable{

    public ManagerDataFile(String filePath) {
        super(filePath);
    }
    public ManagerDataFile() {
        super("");
    }

    @Override
    public Manager createUserFromData(String[] data) {
        if (data.length == 5) {
            return new Manager(data[0], data[1], data[2], data[3], data[4]);
        }
        return null;
    }

    public Manager getManagerById(String mgId){
        List<Manager> managers = getUserList();
        if (managers.isEmpty()) {
            System.out.println("No manager found.");
        } else {
            for (Manager manager : managers) {
                if (manager.getFullName().equals(mgId)){
                    return manager;
                }
            }
        }
        return null;

    }

    
    @Override
    public String generateNewUserId() {
        loadSerializedData();
        return "MG" + (lastUserId + 1);
    }

    @Override
    public String userToString(Manager user) {
        return user.getUserId() + "," + super.toString(user); 
    }

    public void deleteManager(String managerId) {
        deleteUserInFile(managerId);  
    }

    @Override
    public Manager createNewUser(String userId, String fullName, String email, String password, String phone) {
        return new Manager(userId, fullName, email, password, phone);
    }
}
