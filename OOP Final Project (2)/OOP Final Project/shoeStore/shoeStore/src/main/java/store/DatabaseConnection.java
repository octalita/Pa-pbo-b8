package store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // private static final String URL = "jdbc:mysql://localhost:3306/store";
    // private static final String URL = "jdbc:mysql://localhost:3306/storedb";
    // private static final String URL = "jdbc:mysql://localhost:3306/ujicoba";
    private static final String URL = "jdbc:mysql://localhost:3306/shoesstore";
    private static final String USER = "root";
    // hapus password
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
