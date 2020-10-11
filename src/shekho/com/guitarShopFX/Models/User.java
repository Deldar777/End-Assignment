package shekho.com.guitarShopFX.Models;

public class User extends Person{
    private String username;
    private String password;
    private Role role;

    public User(String firstName, String lastName, int phoneNumber, String email,Role role) {
        super(firstName, lastName, phoneNumber, email);
        this.role = role;
    }
}
