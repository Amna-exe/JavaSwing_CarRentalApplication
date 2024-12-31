
public interface UserDataHandler<T extends User> {

    void addUser(T newUserObject);
    void saveSerializedData();
    void loadSerializedData();
    boolean deleteUserInFile(String userId);
    boolean updateUserInFile(String userId, String fullName, String email, String password, String phone);

}
