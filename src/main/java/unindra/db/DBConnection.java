package unindra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("db.properties file not found.");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException("Failed to load database configuration.", e);
        }

        String url = properties.getProperty("db.url", "jdbc:mysql://localhost:3306/space_rental");
        String user = properties.getProperty("db.username", "root");
        String password = properties.getProperty("db.password", "");

        return DriverManager.getConnection(url, user, password);
    }
}
