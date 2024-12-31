import java.util.HashMap;

final class ManagerLogin {
    private final ManagerDataFile managerDetails;
    private final HashMap<String, Manager> managerLoginMap;

    // Constructor
    public ManagerLogin() {
        this.managerDetails = new ManagerDataFile("ManagerData.txt"); 
        this.managerLoginMap = new HashMap<>();
        loadManagerData(); 
    }


    public void loadManagerData() {

        if (managerDetails.getUserList() != null) {
            for (Manager manager : managerDetails.getUserList()) {

                managerLoginMap.put(manager.getUserId(), manager);
            }
        }
    }

    public Manager getManager(String managerId) throws ManagerNotFoundException {
        Manager mg = managerLoginMap.get(managerId);
        if (mg == null) {
            throw new ManagerNotFoundException("Manager with ID " + managerId + " not found.");
        }
        return mg;
    }


    public HashMap<String, Manager> getManagerLoginMap() {
        return managerLoginMap;
    }


    public boolean validateLogin(String managerId, String password) throws InvalidLoginException {
        loadManagerData();
        Manager mg = managerLoginMap.get(managerId);
        if (mg == null || !mg.getPassword().equals(password)) {
            throw new InvalidLoginException("Invalid ManagerId or Password.");
        }
        return true;
    }

}
