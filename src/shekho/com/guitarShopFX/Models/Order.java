package shekho.com.guitarShopFX.Models;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int orderNumber;
    private String date;
    private Customer customer;
    private int quantity;
    private List<Article> articles;
    private int count;
    private double totalPrice;

    private String name;
    private String city;
    private String phone;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerName() {
        return name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Order(Customer customer, List<Article> articles, int orderNumber) {
        this.customer = customer;
        this.articles = articles;
        this.orderNumber = orderNumber;

        date = LocalDate.now().toString();
        quantity = getQuantity();
        name = customer.getFullName();
        city = customer.getCity();
        email = customer.getEmail();
        phone = customer.getPhoneNumber();
        count = getCount();
        totalPrice = getTotalPrice();
    }

    public double getTotalPrice(){

        double total = 0;
        for (Article a:articles
        ) {
            total += a.getPrice() * a.getNumber();
        }

        return total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        quantity = articles.size();
    }

    public int getCount(){
       return articles.size();
    }
    public List<Article> getArticles() {
        return articles;
    }
}
