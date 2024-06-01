package store;

public class Sport extends Shoe {
    private String sportType; // Type of sport (e.g., basketball, soccer)

    public Sport(String brand, int size, String color, int price, int stock, String sportType) {
        super(brand, size, color, price, stock);
        this.sportType = sportType;
    }

    // Getter and Setter
    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Sport Type: " + sportType;
    }
}
