import java.io.*;


public class User implements Serializable{
    public String fullName;
    public String email;
    public String phoneNumber;
    public String password;
    private static final long serialVersionUID = 1L;


    public User(String fullname, String email, String password, String phoneNumber) {
        this.fullName = fullname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    
    public User() {
        this.fullName = null;
        this.email = null;
        this.password = null;
        this.phoneNumber = null;
    }

    //setters and getters

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getUserId() {
        throw new UnsupportedOperationException("This method should be overridden in subclasses");
    }
    @Override
    public String toString() {
        return fullName + "," + email + "," + password +"," + phoneNumber;
    }
}
