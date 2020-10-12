package shekho.com.guitarShopFX.Models;

public class User extends Person{
    private String username;
    private String password;
    private Role role;

    public User(String username, String password , String firstName, String lastName, String phoneNumber, String email, Role role) {
        super(firstName, lastName, phoneNumber, email);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
