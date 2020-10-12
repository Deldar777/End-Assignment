package shekho.com.guitarShopFX.Models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private String streetAddress;
    private String city;
    private List<Order> orders = new ArrayList<>();

    public Customer(String firstName, String lastName, String phoneNumber, String email, String streetAddress, String city) {
        super(firstName, lastName, phoneNumber, email);
        this.streetAddress = streetAddress;
        this.city = city;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
