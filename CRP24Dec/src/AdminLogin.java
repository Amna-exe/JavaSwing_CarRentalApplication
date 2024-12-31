
import java.util.HashMap;
final class AdminLogin {
    protected final HashMap<String, Admin> adminLoginMap;

    protected AdminLogin() {
        adminLoginMap = new HashMap<>();
    }

    protected void loadLoginMap() {
        adminLoginMap.put("AD1", new Admin("AD1", "Amna Farooq", "amnafarooq@gmail.com", "123", "02345678910"));
        adminLoginMap.put("AD2", new Admin("AD2", "Hafsa Ayesha", "hafsaayesha081@gmail.com", "1234", "45672912345"));
    }

    protected Admin getAdmin(String adminId) {
        return adminLoginMap.get(adminId);
    }

    protected boolean validateLogin(String adminId, String adminPass) {
        loadLoginMap();
        Admin admin = adminLoginMap.get(adminId);
        return admin != null && admin.checkPassword(adminPass);
    }
}
