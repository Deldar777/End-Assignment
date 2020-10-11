package shekho.com.guitarShopFX.Models;

public class Person {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;

    public Person(String firstName, String lastName, int phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName(){
        return firstName +" "+lastName;
    }
}
