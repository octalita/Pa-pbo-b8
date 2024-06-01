package store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/shoesstore";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public UserDAO() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                // user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }
        return null;
    }

    // public void addUser(User user) throws SQLException {
    //     String query = "INSERT INTO users (username, password) VALUES (?, ?)";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //         preparedStatement.setString(1, user.getUsername());
    //         preparedStatement.setString(2, user.getPassword());
    //         preparedStatement.executeUpdate();
    //     }
    // }
}