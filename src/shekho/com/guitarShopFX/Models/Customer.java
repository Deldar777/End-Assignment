package shekho.com.guitarShopFX.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    private String streetAddress;
    private String city;
    private List<Order> orders = new ArrayList<>();

    public Customer(String firstName, String lastName, String streetAddress,
                    String city, String phoneNumber, String email) {
        super(firstName, lastName, phoneNumber, email);
        this.streetAddress = streetAddress;
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
