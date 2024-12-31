import java.io.*;
import java.util.*;

public abstract class UserDataFile<T extends User> implements UserDataHandler<T>{
    public List<T> userList;
    public String filePath; 
    public int lastUserId = 0;

    public UserDataFile(String filePath) {
        this.filePath = filePath;
        this.userList = new ArrayList<>();
        loadSerializedData();
    }
    public UserDataFile() {
        this.userList = new ArrayList<>();
        this.filePath = "";
    }

    // Load serialized data

    @SuppressWarnings("unchecked")
    @Override
    public final void loadSerializedData() {
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            try (var ois = new ObjectInputStream(new FileInputStream(file))) {
                userList = (List<T>) ois.readObject();
                if (!userList.isEmpty()) {
                    lastUserId = 0;  
                    for (T user : userList) {
                        int userId = parseNumericPart(user.getUserId());
                        if (userId > lastUserId) {
                            lastUserId = userId; 
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading serialized data: " + e.getMessage());
            }
        } else {
            saveSerializedData();
        }
    }

    // Parse numeric part of user ID
    private int parseNumericPart(String userId) {
        String numericPartString = userId.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(numericPartString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + userId);
            return 0;
        }
    }

    // Save serialized data
    @Override
    public void saveSerializedData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(userList);
        } catch (IOException e) {
            System.out.println("Error saving serialized data: " + e.getMessage());
        }
    }

    

    // Add a new user
    @Override
    public void addUser(T newUserObject) {
        String newUserId = generateNewUserId();

        String fullName = newUserObject.getFullName();
        String email = newUserObject.getEmail();
        String password = newUserObject.getPassword();
        String phone = newUserObject.getPhoneNumber();

        T newUser = createNewUser(newUserId, fullName, email, password, phone);

        userList.add(newUser);
        saveSerializedData();
    }

    public void saveUserToFile(T user) {
        userList.add(user);
        saveSerializedData();
    }

    @Override
    public boolean deleteUserInFile(String userId) {
        for (Iterator<T> iterator = userList.iterator(); iterator.hasNext(); ) {
            T user = iterator.next();
            if (user.getUserId().equals(userId)) {
                iterator.remove();  
                saveSerializedData(); 
                return true; 
            }
        }
        return false;  
    }

    @Override
    public boolean updateUserInFile(String userId, String fullName, String email, String password, String phone) {
        for (T user : userList) {
            if (user.getUserId().equals(userId)) {
                user.setFullName(fullName);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhoneNumber(phone);
                saveSerializedData();
                return true;
            }
        }
        return false;
    }

    // Update entire file (re-save the list)
    public void updateFile() {
        saveSerializedData();
    }

    // Abstract methods to implement in subclasses
    public abstract T createNewUser(String userId, String fullName, String email, String password, String phone);
    public abstract T createUserFromData(String[] data);
    public abstract String generateNewUserId();
    public abstract String userToString(T user);

    public List<T> getUserList() {
        return userList;
    }

    public String toString(T user) {
        return user.getFullName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getPhoneNumber();
    }
}
