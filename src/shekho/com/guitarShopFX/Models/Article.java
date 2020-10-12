package shekho.com.guitarShopFX.Models;

public class Article {

    private int id;
    private String brand;
    private String model;
    private boolean acoustic;
    private TypeGuitar type;
    private double price;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int counter = 0;

    public Article(String brand, String model, boolean acoustic, TypeGuitar type, double price, int quantity) {
        id = getId();
        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
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
