package shekho.com.guitarShopFX.Models;

import java.io.Serializable;

public class Article implements Serializable {

    private String brand;
    private String model;
    private boolean acoustic;
    private TypeGuitar type;
    private double price;
    private int quantity;
    private String id;


    public Article(String brand, String model, boolean acoustic, TypeGuitar type, double price, int quantity,String id) {

        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getId() {return id; }
    public boolean isAcoustic() {
        return acoustic;
    }


}
