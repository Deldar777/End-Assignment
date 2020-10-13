package shekho.com.guitarShopFX.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Article {


    private int id;
    private String brand;
    private String model;
    private boolean acoustic;
    private TypeGuitar type;
    private double price;
    private int quantity;
    private int number;

    private int counter = 32324;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public Article(String brand, String model, boolean acoustic, TypeGuitar type, double price, int quantity) {

        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        id = getId();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAcoustic() {
        return acoustic;
    }

    public TypeGuitar getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public int getId(){
        counter++;
        return counter;
    }

}
