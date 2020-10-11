package shekho.com.guitarShopFX.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNumber;
    private LocalDateTime date;
    private Customer customer;
    private int quantity;
    private List<Article> articles = new ArrayList<>();
    private double totalPrice;

    private int counter = 0;

    public Order(Customer customer, int quantity) {
        this.customer = customer;
        this.quantity = quantity;
        date = LocalDateTime.now();
        orderNumber = getOrderNumber();
        totalPrice = getTotalPrice();
    }

    public int getOrderNumber(){
        counter++;
        return counter;
    }

    private double getTotalPrice(){
        double total = 0;

        if(articles.size() != 0){

            for (Article a:articles
                 ) {
                total += a.getPrice();
            }
        }
        return total;
    }


    public int getQuantity() {
        return quantity;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
