package shekho.com.guitarShopFX.Models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private int orderNumber;
    private String date;
    private Customer customer;
    private HashMap<Article,Integer> articles;
    private int count;
    private double totalPrice;


    public double getTotalPrice() {
        double total = 0;

        for (Map.Entry<Article,Integer> entry:articles.entrySet()
             ) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    private int orderCounter = 100;

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }
    public Customer getCustomer() {
        return customer;
    }


    public Order(Customer customer,HashMap<Article,Integer> articles) {

        this.articles = articles;
        this.customer = customer;

        orderNumber = createOrderNumber();
        date = LocalDate.now().toString();
        count = articles.size();
        totalPrice = getTotalPrice();
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    public int createOrderNumber(){
        orderCounter++;
        return orderCounter;
    }

    public HashMap<Article,Integer> getArticles() {
        return articles;
    }

}
