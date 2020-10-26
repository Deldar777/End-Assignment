package shekho.com.guitarShopFX.Models;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private int orderNumber;
    private String date;
    private Customer customer;
    private HashMap<Article,Integer> articles;
    private int count;
    private double totalPrice;


    public Order(Customer customer, HashMap<Article,Integer> articles, int orderNumber) {

        this.articles = articles;
        this.customer = customer;
        this.orderNumber = orderNumber;

        date = LocalDate.now().toString();
        count = articles.size();
        totalPrice = getTotalPrice();
    }

    public HashMap<Article,Integer> getArticles() {
        return articles;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getOrderNumber() { return orderNumber; }
    public int getCount() {
        return count;
    }
    public String getDate() {
        return date;
    }

    public double getTotalPrice() {
        double total = 0;

        for (Map.Entry<Article,Integer> entry:articles.entrySet()
        ) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
