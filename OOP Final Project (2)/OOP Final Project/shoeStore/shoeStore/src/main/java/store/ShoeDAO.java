package store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoeDAO {
    public void addShoe(Shoe shoe) throws SQLException {
        String sql = "INSERT INTO shoes (brand, size, color, price, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, shoe.getBrand());
            statement.setInt(2, shoe.getSize());
            statement.setString(3, shoe.getColor());
            statement.setInt(4, shoe.getPrice());
            statement.setInt(5, shoe.getStock());
            statement.executeUpdate();
        }
    }

    public Shoe getShoe(int id) throws SQLException {
        String sql = "SELECT * FROM shoes WHERE id = ?";
        Shoe shoe = null;
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                int size = resultSet.getInt("size");
                String color = resultSet.getString("color");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                String type = resultSet.getString("type");
    
                if ("Running".equals(type)) {
                    double weight = resultSet.getDouble("weight");
                    shoe = new Running(brand, size, color, price, stock, weight);
                } else if ("Sport".equals(type)) {
                    String sportType = resultSet.getString("sportType");
                    shoe = new Sport(brand, size, color, price, stock, sportType);
                } else if ("Casual".equals(type)) {
                    String material = resultSet.getString("material");
                    shoe = new Casual(brand, size, color, price, stock, material);
                } else {
                    shoe = new Shoe(brand, size, color, price, stock); // Fallback case for generic shoe
                }
                shoe.setId(id); // Setting the ID for the shoe
            }
        }
        return shoe;
    }
    

    // coba

    // public List<Shoe> getAllShoes() throws SQLException {
    //     List<Shoe> shoes = new ArrayList<>();
    //     String sql = "SELECT * FROM shoes ORDER BY brand ASC";

    //     try (Connection connection = DatabaseConnection.getConnection();
    //          Statement statement = connection.createStatement();
    //          ResultSet resultSet = statement.executeQuery(sql)) {
    //         while (resultSet.next()) {
    //             Shoe shoe = new Shoe();
    //             shoe.setId(resultSet.getInt("id"));
    //             shoe.setBrand(resultSet.getString("brand"));
    //             shoe.setSize(resultSet.getInt("size"));
    //             shoe.setColor(resultSet.getString("color"));
    //             shoe.setPrice(resultSet.getInt("price"));
    //             shoe.setStock(resultSet.getInt("stock"));
    //             // shoe.set(resultSet.getString("type"));
    //             shoes.add(shoe);
    //         }
    //     }
    //     return shoes;
    // }

    // coba 
    public List<Shoe> getAllShoes() throws SQLException {
        List<Shoe> shoes = new ArrayList<>();
        String sql = "SELECT * FROM shoes";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                Shoe shoe;
                if ("Running".equalsIgnoreCase(type)) {
                    shoe = new Running(
                        resultSet.getString("brand"),
                        resultSet.getInt("size"),
                        resultSet.getString("color"),
                        resultSet.getInt("price"),
                        resultSet.getInt("stock"),
                        resultSet.getDouble("weight")
                    );
                } else if ("Sport".equalsIgnoreCase(type)) {
                    shoe = new Sport(
                        resultSet.getString("brand"),
                        resultSet.getInt("size"),
                        resultSet.getString("color"),
                        resultSet.getInt("price"),
                        resultSet.getInt("stock"),
                        resultSet.getString("sportType")
                    );
                } else if ("Casual".equalsIgnoreCase(type)) {
                    shoe = new Casual(
                        resultSet.getString("brand"),
                        resultSet.getInt("size"),
                        resultSet.getString("color"),
                        resultSet.getInt("price"),
                        resultSet.getInt("stock"),
                        resultSet.getString("material")
                    );
                } else {
                    shoe = new Shoe(
                        resultSet.getString("brand"),
                        resultSet.getInt("size"),
                        resultSet.getString("color"),
                        resultSet.getInt("price"),
                        resultSet.getInt("stock")
                    );
                }
                shoe.setId(resultSet.getInt("id"));
                shoes.add(shoe);
            }
        }
        return shoes;
    }

    public void updateShoe(Shoe shoe) throws SQLException {
        String sql = "UPDATE shoes SET brand = ?, size = ?, color = ?, price = ?, stock = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, shoe.getBrand());
            statement.setInt(2, shoe.getSize());
            statement.setString(3, shoe.getColor());
            statement.setInt(4, shoe.getPrice());
            statement.setInt(5, shoe.getStock());
            statement.setInt(6, shoe.getId());
            statement.executeUpdate();
        }
    }

    public void deleteShoe(int id) throws SQLException {
        String sql = "DELETE FROM shoes WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // running, sport, casual 
    // public void addRunningShoe(Running runningShoe) throws SQLException {
    //     String sql = "INSERT INTO shoes (brand, size, color, price, stock, weight, type) VALUES (?, ?, ?, ?, ?, ?, 'Running')";
    //     try (Connection connection = DatabaseConnection.getConnection();
    //          PreparedStatement statement = connection.prepareStatement(sql)) {
    //         statement.setString(1, runningShoe.getBrand());
    //         statement.setInt(2, runningShoe.getSize());
    //         statement.setString(3, runningShoe.getColor());
    //         statement.setInt(4, runningShoe.getPrice());
    //         statement.setInt(5, runningShoe.getStock());
    //         statement.setDouble(6, runningShoe.getWeight());
    //         statement.executeUpdate();
    //     }
    // }

    // public void addSportShoe(Sport sportShoe) throws SQLException {
    //     String sql = "INSERT INTO shoes (brand, size, color, price, stock, sportType, type) VALUES (?, ?, ?, ?, ?, ?, 'Sport')";
    //     try (Connection connection = DatabaseConnection.getConnection();
    //          PreparedStatement statement = connection.prepareStatement(sql)) {
    //         statement.setString(1, sportShoe.getBrand());
    //         statement.setInt(2, sportShoe.getSize());
    //         statement.setString(3, sportShoe.getColor());
    //         statement.setInt(4, sportShoe.getPrice());
    //         statement.setInt(5, sportShoe.getStock());
    //         statement.setString(6, sportShoe.getSportType());
    //         statement.executeUpdate();
    //     }
    // }

    // public void addCasualShoe(Casual casualShoe) throws SQLException {
    //     String sql = "INSERT INTO shoes (brand, size, color, price, stock, material, type) VALUES (?, ?, ?, ?, ?, ?, 'Casual')";
    //     try (Connection connection = DatabaseConnection.getConnection();
    //          PreparedStatement statement = connection.prepareStatement(sql)) {
    //         statement.setString(1, casualShoe.getBrand());
    //         statement.setInt(2, casualShoe.getSize());
    //         statement.setString(3, casualShoe.getColor());
    //         statement.setInt(4, casualShoe.getPrice());
    //         statement.setInt(5, casualShoe.getStock());
    //         statement.setString(6, casualShoe.getMaterial());
    //         statement.executeUpdate();
    //     }
    // }

    // masih broken
    public List<Shoe> getShoesByBrand(String brand) throws SQLException {
        List<Shoe> shoes = new ArrayList<>();
        String sql = "SELECT * FROM shoes WHERE brand = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, brand);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                Shoe shoe;
                switch (type) {
                    case "Running":
                        shoe = new Running(
                            resultSet.getString("brand"),
                            resultSet.getInt("size"),
                            resultSet.getString("color"),
                            resultSet.getInt("price"),
                            resultSet.getInt("stock"),
                            resultSet.getDouble("weight")
                        );
                        break;
                    case "Sport":
                        shoe = new Sport(
                            resultSet.getString("brand"),
                            resultSet.getInt("size"),
                            resultSet.getString("color"),
                            resultSet.getInt("price"),
                            resultSet.getInt("stock"),
                            resultSet.getString("sportType")
                        );
                        break;
                    case "Casual":
                        shoe = new Casual(
                            resultSet.getString("brand"),
                            resultSet.getInt("size"),
                            resultSet.getString("color"),
                            resultSet.getInt("price"),
                            resultSet.getInt("stock"),
                            resultSet.getString("material")
                        );
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown shoe type: " + type);
                }
                shoe.setId(resultSet.getInt("id"));
                shoes.add(shoe);
            }
        }
        return shoes;
    }

    // add run, sport, cas 

    // public void addRunningShoe(Running runningShoe) throws SQLException {
    //     String shoeSql = "INSERT INTO shoes (brand, size, color, price, stock, type) VALUES (?, ?, ?, ?, ?, 'Running')";
    //     String runningSql = "INSERT INTO running_shoes (shoe_id, weight) VALUES (?, ?)";
        
    //     try (Connection connection = DatabaseConnection.getConnection()) {
    //         connection.setAutoCommit(false);
    //         try (PreparedStatement shoeStatement = connection.prepareStatement(shoeSql, Statement.RETURN_GENERATED_KEYS)) {
    //             shoeStatement.setString(1, runningShoe.getBrand());
    //             shoeStatement.setInt(2, runningShoe.getSize());
    //             shoeStatement.setString(3, runningShoe.getColor());
    //             shoeStatement.setInt(4, runningShoe.getPrice());
    //             shoeStatement.setInt(5, runningShoe.getStock());
    //             shoeStatement.executeUpdate();
                
    //             ResultSet generatedKeys = shoeStatement.getGeneratedKeys();
    //             if (generatedKeys.next()) {
    //                 int shoeId = generatedKeys.getInt(1);
    //                 try (PreparedStatement runningStatement = connection.prepareStatement(runningSql)) {
    //                     runningStatement.setInt(1, shoeId);
    //                     runningStatement.setDouble(2, runningShoe.getWeight());
    //                     runningStatement.executeUpdate();
    //                 }
    //             }
    //             connection.commit();
    //         } catch (SQLException e) {
    //             connection.rollback();
    //             throw e;
    //         }
    //     }
    // }
    public void addRunningShoe(Running runningShoe) throws SQLException {
        String sql = "INSERT INTO shoes (brand, size, color, price, stock, type, weight) VALUES (?, ?, ?, ?, ?, 'Running', ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, runningShoe.getBrand());
            statement.setInt(2, runningShoe.getSize());
            statement.setString(3, runningShoe.getColor());
            statement.setInt(4, runningShoe.getPrice());
            statement.setInt(5, runningShoe.getStock());
            statement.setDouble(6, runningShoe.getWeight());
            statement.executeUpdate();
        }
    }

    // Method to add Sport Shoe
    public void addSportShoe(Sport sportShoe) throws SQLException {
        String shoeSql = "INSERT INTO shoes (brand, size, color, price, stock, type) VALUES (?, ?, ?, ?, ?, 'Sport')";
        String sportSql = "INSERT INTO sport_shoes (shoe_id, sportType) VALUES (?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement shoeStatement = connection.prepareStatement(shoeSql, Statement.RETURN_GENERATED_KEYS)) {
                shoeStatement.setString(1, sportShoe.getBrand());
                shoeStatement.setInt(2, sportShoe.getSize());
                shoeStatement.setString(3, sportShoe.getColor());
                shoeStatement.setInt(4, sportShoe.getPrice());
                shoeStatement.setInt(5, sportShoe.getStock());
                shoeStatement.executeUpdate();
                
                ResultSet generatedKeys = shoeStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int shoeId = generatedKeys.getInt(1);
                    try (PreparedStatement sportStatement = connection.prepareStatement(sportSql)) {
                        sportStatement.setInt(1, shoeId);
                        sportStatement.setString(2, sportShoe.getSportType());
                        sportStatement.executeUpdate();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    // Method to add Casual Shoe
    public void addCasualShoe(Casual casualShoe) throws SQLException {
        String shoeSql = "INSERT INTO shoes (brand, size, color, price, stock, type) VALUES (?, ?, ?, ?, ?, 'Casual')";
        String casualSql = "INSERT INTO casual_shoes (shoe_id, material) VALUES (?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement shoeStatement = connection.prepareStatement(shoeSql, Statement.RETURN_GENERATED_KEYS)) {
                shoeStatement.setString(1, casualShoe.getBrand());
                shoeStatement.setInt(2, casualShoe.getSize());
                shoeStatement.setString(3, casualShoe.getColor());
                shoeStatement.setInt(4, casualShoe.getPrice());
                shoeStatement.setInt(5, casualShoe.getStock());
                shoeStatement.executeUpdate();
                
                ResultSet generatedKeys = shoeStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int shoeId = generatedKeys.getInt(1);
                    try (PreparedStatement casualStatement = connection.prepareStatement(casualSql)) {
                        casualStatement.setInt(1, shoeId);
                        casualStatement.setString(2, casualShoe.getMaterial());
                        casualStatement.executeUpdate();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    

}
