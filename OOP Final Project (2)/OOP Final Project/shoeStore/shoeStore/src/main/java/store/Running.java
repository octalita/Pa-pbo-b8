    package store;

    public class Running extends Shoe {
        private double weight; // Weight of the shoe in grams

        public Running(String brand, int size, String color, int price, int stock, double weight) {
            super(brand, size, color, price, stock);
            this.weight = weight;
        }

        // Getter and Setter
        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return super.toString() + ", Weight: " + weight + " grams";
        }
    }
