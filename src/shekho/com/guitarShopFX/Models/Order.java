package shekho.com.guitarShopFX.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Order {

    private int orderNumber;
    private String date;
    private Customer customer;
    private int quantity;
    private Map<Article,Integer> articles;
    private int count;
    private double totalPrice;


    private int orderCounter = 100;


    public String getDate() {
        return date;
    }
    public Customer getCustomer() {
        return customer;
    }

    public Order() {

        date = LocalDate.now().toString();
        quantity = getQuantity();
        count = getCount();
        //totalPrice = getTotalPrice();
    }

    /*public double getTotalPrice(){

        double total = 0;
        for (Article a:articles
        ) {
            total += a.getPrice() * a.getNumber();
        }

        return total;
    }*/

    public int getOrderNumber() {
        orderCounter++;
        return orderCounter;
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
    public Map<Article,Integer> getArticles() {
        return articles;
    }
}
