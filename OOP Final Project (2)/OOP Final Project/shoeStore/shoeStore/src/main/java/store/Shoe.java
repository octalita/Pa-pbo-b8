package store;

public class Shoe implements ShoeInterface {
    private int id;
    private String brand;
    private int size;
    private String color;
    private int Price;
    private int Stock;

    // Constructors, getters, and setters

    public Shoe() {}

    public Shoe(String brand, int size, String color, int price, int stock) {
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.Price = price;
        this.Stock = stock;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getPrice() {
        return Price;
    }

    @Override
    public void setPrice(int price) {
        this.Price = price;
    }

    @Override
    public int getStock() {
        return Stock;
    }

    @Override
    public void setStock(int stock) {
        this.Stock = stock;
    }

    @Override
    public String toString() {
        return "Shoe ID: " + id + ", Brand: " + brand + ", Size: " + size + ", Color: " + color + ", Price: " + Price + ", Stock: " + Stock;
    }
}
