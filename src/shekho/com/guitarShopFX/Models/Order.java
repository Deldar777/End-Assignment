package shekho.com.guitarShopFX.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Order {

    private int orderNumber;
    private String date;
    private Customer customer;
    private int quantity;
    private List<Article> articles;
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

    public Order(Customer customer,List<Article> articles) {

        this.articles = articles;
        this.customer = customer;

        orderNumber = createOrderNumber();
        date = LocalDate.now().toString();
        //quantity = setQuantity();
        totalPrice = getTotalPrice();
    }

    public double getTotalPrice(){

        double total = 0;
        for (Article a:articles
        ) {
            total += a.getPrice();
        }

        return total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    public int createOrderNumber(){
        orderCounter++;
        return orderCounter;
    }

    public int getQuantity() {
        int quantity = 0;
        for (Article a: articles
             ) {
            if(!articles.contains(a)){
                quantity++;
            }
        }
        return quantity;
    }


    public int getCount(){
       return articles.size();
    }

    public List<Integer> getArticlesNumber() {
        return articlesNumber;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(){
        Article article = null;

        for (Article a:articles
             ) {
            article = a;
        }
        return article;
    }
}
