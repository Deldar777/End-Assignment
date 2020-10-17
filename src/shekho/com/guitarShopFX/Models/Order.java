package shekho.com.guitarShopFX.Models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private int orderNumber;
    private String date;
    private Customer customer;
    private int quantity;
    private HashMap<Article,Integer> articles;
    private List<Integer> articlesNumber;
    private double totalPrice;


    private int orderCounter = 100;


    public String getDate() {
        return date;
    }
    public Customer getCustomer() {
        return customer;
    }

    /*public int setQuantity() {
       return quantity = articlesNumber.size();
    }*/

    public Order(Customer customer,HashMap<Article,Integer> articles) {

        this.articles = articles;
        this.customer = customer;

        orderNumber = createOrderNumber();
        date = LocalDate.now().toString();
        //quantity = setQuantity();
        //totalPrice = getTotalPrice();
    }

   /* public double getTotalPrice(){

        double total = 0;
        for (Article a:articles
        ) {
            total += a.getPrice();
        }

        return total;
    }*/

    public int getOrderNumber() {
        return orderNumber;
    }
    public int createOrderNumber(){
        orderCounter++;
        return orderCounter;
    }

   /* public int getQuantity() {
        int quantity = 0;
        for (Article a: articles
             ) {
            if(!articles.contains(a)){
                quantity++;
            }
        }
        return quantity;
    }*/


    public int getCount(){
       return articles.size();
    }

    public List<Integer> getArticlesNumber() {
        return articlesNumber;
    }

    public HashMap<Article,Integer> getArticles() {
        return articles;
    }

}
