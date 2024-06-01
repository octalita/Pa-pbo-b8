package store;

public interface ShoeInterface {
    int getId();
    void setId(int id);
    
    String getBrand();
    void setBrand(String brand);
    
    int getSize();
    void setSize(int size);
    
    String getColor();
    void setColor(String color);
    
    int getPrice();
    void setPrice(int price);
    
    int getStock();
    void setStock(int stock);
    
    @Override
    String toString();
}
