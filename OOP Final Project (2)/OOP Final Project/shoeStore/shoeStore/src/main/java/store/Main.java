package store;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        ShoeDAO shoeDAO = new ShoeDAO();
        Scanner scanner = new Scanner(System.in);

        User currentUser = null;
        while (currentUser == null) {
            System.out.println("Please login to continue.");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            try {
                currentUser = userDAO.getUserByUsernameAndPassword(username, password);
                if (currentUser == null) {
                    System.out.println("Invalid username or password. Please try again.");
                } else {
                    System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.println("\nChoose an operation: ");
            System.out.println("1. Add Shoe");
            System.out.println("2. Get Shoe by Id");
            System.out.println("3. Get All Shoes");
            System.out.println("4. Update Shoe");
            System.out.println("5. Delete Shoe");
            System.out.println("6. Get Shoe by Brand"); // broken
            System.out.println("7. Get Specific Shoe Info");
            System.out.println("8. Exit");
            System.out.print("Choose  : ");
            int choice = scanner.nextInt();

            scanner.nextLine(); // Consume newline

            try {
                switch (choice) { // masih broken
                    case 1:
                        // Add Shoe
                        System.out.println("Select shoe type:");
                        System.out.println("1. Running");
                        System.out.println("2. Sport");
                        System.out.println("3. Casual");
                        System.out.print("Choose  :  ");
                        int typeChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                       
                        String brand;
                        while (true) {
                            System.out.print("Brand  : ");
                            brand = scanner.nextLine();
                            if (brand.matches("[a-zA-Z]+")) { // Cek apakah hanya mengandung huruf
                                break;
                            } else {
                                System.out.println("Invalid input for color. Please enter letters only.");
                            }
                        }
                        int size;
                        while (true) {
                            System.out.print("Size  : ");
                            String sizeInput = scanner.nextLine();
                            if (sizeInput.matches("\\d+")) { // Cek apakah hanya mengandung angka
                                size = Integer.parseInt(sizeInput);
                                break;
                            } else {
                                System.out.println("Invalid input for size. Please enter numbers only.");
                            }
                        }
                        String color;
                        while (true) {
                            System.out.print("Color  : ");
                            color = scanner.nextLine();
                            if (color.matches("[a-zA-Z]+")) { // Cek apakah hanya mengandung huruf
                                break;
                            } else {
                                System.out.println("Invalid input for color. Please enter letters only.");
                            }
                        }
                        int price;
                        while (true) {
                            System.out.print("Price  : ");
                            String priceInput = scanner.nextLine();
                            if (priceInput.matches("\\d+")) { // Cek apakah hanya mengandung angka
                                price = Integer.parseInt(priceInput);
                                break;
                            } else {
                                System.out.println("Invalid input for size. Please enter numbers only.");
                            }
                        }
                        int stock;
                        while (true) {
                            System.out.print("Stock  : ");
                            String stockInput = scanner.nextLine();
                            if (stockInput.matches("\\d+")) { // Cek apakah hanya mengandung angka
                                stock = Integer.parseInt(stockInput);
                                break;
                            } else {
                                System.out.println("Invalid input for size. Please enter numbers only.");
                            }
                        }

                        switch (typeChoice) {
                            case 1:
                                // Add Running Shoe
                                double weight;
                                while (true) {
                                    System.out.print("Weight (grams): ");
                                    String weightInput = scanner.nextLine();
                                    try {
                                        weight = Double.parseDouble(weightInput);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input for weight. Please enter numbers only.");
                                    }
                                }
                                Running runningShoe = new Running(brand, size, color, price, stock, weight);
                                shoeDAO.addRunningShoe(runningShoe);
                                break;
                            case 2:
                                // Add Sport Shoe
                                System.out.print("Sport type: ");
                                String sportType = scanner.nextLine();
                                Sport sportShoe = new Sport(brand, size, color, price, stock, sportType);
                                shoeDAO.addSportShoe(sportShoe);
                                break;
                            case 3:
                                // Add Casual Shoe
                                System.out.print("Material: ");
                                String material = scanner.nextLine();
                                Casual casualShoe = new Casual(brand, size, color, price, stock, material);
                                shoeDAO.addCasualShoe(casualShoe);
                                break;
                            default:
                                System.out.println("Invalid shoe type. Please try again.");
                                continue;
                        }
                        System.out.println("Shoe added successfully!");
                        break;

                    case 2:
                        // Get Shoe by ID
                        System.out.print("Shoe ID  : ");
                        int id = scanner.nextInt();
                        Shoe shoe = shoeDAO.getShoe(id);
                        if (shoe != null) {
                            System.out.println("--- here's the shoe ---");
                            System.out.println("Shoe ID: " + shoe.getId());
                            System.out.println("Brand: " + shoe.getBrand());
                            System.out.println("Size: " + shoe.getSize());
                            System.out.println("Color: " + shoe.getColor());
                            System.out.println("Price: $" + shoe.getPrice());
                            System.out.println("Stock: " + shoe.getStock());
                            System.out.println("-----------------------");
                        } else {
                            System.out.println("Shoe not found!");
                        }
                        break;

                    case 3:
                        List<Shoe> shoes = shoeDAO.getAllShoes();

                        System.out.printf("%-10s %-20s %-10s %-10s %-20s %-10s\n", "Shoe ID", "Brand", "Size", "Color",
                                "Price", "Stock");
                        System.out.println(
                                "_____________________________________________________________________________________");

                        for (Shoe s : shoes) {
                            System.out.printf("%-10d %-20s %-10d %-10s %-20s %-10s\n", s.getId(), s.getBrand(),
                                    s.getSize(), s.getColor(), "$" + s.getPrice(), s.getStock());
                            System.out.println(
                                    "-------------------------------------------------------------------------------------");
                        }
                        break;

                    case 4:
                        // Update Shoe
                        List<Shoe> allShoes = shoeDAO.getAllShoes(); // Menggunakan nama variabel yang berbeda shoe
                                                                     // menjadi allshoe

                        // Menampilkan header tabel
                        System.out.printf("%-10s %-20s %-10s %-10s %-20s %-10s\n", "Shoe ID", "Brand", "Size", "Color",
                                "Price", "Stock");
                        System.out.println("_________________________________________________________");

                        // Menampilkan setiap sepatu dalam satu baris
                        for (Shoe s : allShoes) {
                            System.out.printf("%-10d %-20s %-10d %-10s %-20s %-10s\n", s.getId(), s.getBrand(),
                                    s.getSize(), s.getColor(), "$" + s.getPrice(), s.getStock());
                            System.out.println("---------------------------------------------------------");
                        }

                        System.out.print("Shoe ID  : ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Shoe updateShoe = shoeDAO.getShoe(updateId);
                        if (updateShoe != null) {
                            System.out.println("Enter new brand: ");
                            String newBrand;
                            while (true) {
                                newBrand = scanner.nextLine();
                                if (newBrand.matches("[a-zA-Z]+")) { // Cek apakah hanya mengandung huruf
                                    break;
                                } else {
                                    System.out.println("Invalid input for brand. Please enter letters only.");
                                }
                            }
                        
                            int newSize;
                            while (true) {
                                System.out.println("Enter new size: ");
                                String sizeInput = scanner.nextLine();
                                if (sizeInput.matches("\\d+")) { // Cek apakah hanya mengandung angka
                                    newSize = Integer.parseInt(sizeInput);
                                    break;
                                } else {
                                    System.out.println("Invalid input for size. Please enter numbers only.");
                                }
                            }
                        
                            System.out.println("Enter new color: ");
                            String newColor;
                            while (true) {
                                newColor = scanner.nextLine();
                                if (newColor.matches("[a-zA-Z]+")) { // Cek apakah hanya mengandung huruf
                                    break;
                                } else {
                                    System.out.println("Invalid input for color. Please enter letters only.");
                                }
                            }
                        
                            updateShoe.setBrand(newBrand);
                            updateShoe.setSize(newSize);
                            updateShoe.setColor(newColor);
                            shoeDAO.updateShoe(updateShoe);
                            System.out.println("Shoe updated successfully!");
                        } else {
                            System.out.println("Shoe not found!");
                        }
                        

                    case 5:
                        // Delete Shoe

                        List<Shoe> allShoesDelete = shoeDAO.getAllShoes(); // Menggunakan nama variabel yang berbeda

                        // Menampilkan header tabel
                        System.out.printf("%-10s %-20s %-10s %-10s %-20s %-10s\n", "Shoe ID", "Brand", "Size", "Color",
                                "Price", "Stock");
                        System.out.println("_________________________________________________________");

                        // Menampilkan setiap sepatu dalam satu baris
                        for (Shoe s : allShoesDelete) {
                            System.out.printf("%-10d %-20s %-10d %-10s %-20s %-10s\n", s.getId(), s.getBrand(),
                                    s.getSize(), s.getColor(), "$" + s.getPrice(), s.getStock());
                            System.out.println("---------------------------------------------------------");
                        }

                        System.out.print("Shoe ID: ");
                        int deleteId = scanner.nextInt();
                        shoeDAO.deleteShoe(deleteId);
                        System.out.println("Shoe deleted successfully!");
                        System.out.println("---------------------------------------------------------");
                        break;
                    case 6: // broken
                        // Get Shoes by Brand
                        System.out.print("Shoe Brand: ");
                        String searchBrand = scanner.nextLine();
                        List<Shoe> shoesByBrand = shoeDAO.getShoesByBrand(searchBrand);

                        if (shoesByBrand.isEmpty()) {
                            System.out.println("No shoes found for brand: " + searchBrand);
                        } else {
                            // Display shoes
                            for (Shoe s : shoesByBrand) {
                                System.out.println(s);
                            }
                        }
                        break;

                    case 7:
                        // Get Specific Shoe Info
                        System.out.println("Enter shoe ID: ");
                        int specificId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Shoe specificShoe = shoeDAO.getShoe(specificId);
                        if (specificShoe != null) {
                            if (specificShoe instanceof Running) {
                                Running runningShoe = (Running) specificShoe;
                                System.out.println("Running Shoe Info:");
                                System.out.println("ID: " + runningShoe.getId());
                                System.out.println("Brand: " + runningShoe.getBrand());
                                System.out.println("Size: " + runningShoe.getSize());
                                System.out.println("Color: " + runningShoe.getColor());
                                System.out.println("Price: $" + runningShoe.getPrice());
                                System.out.println("Stock: " + runningShoe.getStock());
                                System.out.println("Weight: " + runningShoe.getWeight() + " grams");
                            } else if (specificShoe instanceof Sport) {
                                Sport sportShoe = (Sport) specificShoe;
                                System.out.println("Sport Shoe Info:");
                                System.out.println("ID: " + sportShoe.getId());
                                System.out.println("Brand: " + sportShoe.getBrand());
                                System.out.println("Size: " + sportShoe.getSize());
                                System.out.println("Color: " + sportShoe.getColor());
                                System.out.println("Price: $" + sportShoe.getPrice());
                                System.out.println("Stock: " + sportShoe.getStock());
                                System.out.println("Sport Type: " + sportShoe.getSportType());
                            } else if (specificShoe instanceof Casual) {
                                Casual casualShoe = (Casual) specificShoe;
                                System.out.println("Casual Shoe Info:");
                                System.out.println("ID: " + casualShoe.getId());
                                System.out.println("Brand: " + casualShoe.getBrand());
                                System.out.println("Size: " + casualShoe.getSize());
                                System.out.println("Color: " + casualShoe.getColor());
                                System.out.println("Price: $" + casualShoe.getPrice());
                                System.out.println("Stock: " + casualShoe.getStock());
                                System.out.println("Material: " + casualShoe.getMaterial());
                            } else {
                                System.out.println("Shoe Info:");
                                System.out.println("ID: " + specificShoe.getId());
                                System.out.println("Brand: " + specificShoe.getBrand());
                                System.out.println("Size: " + specificShoe.getSize());
                                System.out.println("Color: " + specificShoe.getColor());
                                System.out.println("Price: $" + specificShoe.getPrice());
                                System.out.println("Stock: " + specificShoe.getStock());
                            }
                        } else {
                            System.out.println("Shoe not found!");
                        }
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // gatau ini apaan
    // broken
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
