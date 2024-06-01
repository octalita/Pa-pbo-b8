package store;

public class Casual extends Shoe {
    private String material; // Material of the shoe (e.g., leather, canvas)

    public Casual(String brand, int size, String color, int price, int stock, String material) {
        super(brand, size, color, price, stock);
        this.material = material;
    }

    // Getter and Setter
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() + ", Material: " + material;
    }
}
