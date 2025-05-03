package unindra.core.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    private static Connection conn;

    public static void openConnection() throws SQLException {
        try {
            String dbHost = Config.get("DB_HOST", "localhost");
            String dbPort = Config.get("DB_PORT", "3306");
            String dbName = Config.get("DB_NAME", "space_rental");
            String dbUser = Config.get("DB_USERNAME", "root");
            String dbPassword = Config.get("DB_PASSWORD", "");
            conn = DriverManager.getConnection(
                "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false&serverTimezone=UTC",
                dbUser,
                dbPassword
            );
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            throw e;
        }
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            openConnection();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper untuk SELECT (hasil ResultSet harus ditutup oleh pemanggil)
    public static ResultSet query(String sql, Object... params) throws SQLException {
        PreparedStatement stmt = prepareStatement(sql, params);
        return stmt.executeQuery();
    }

    // Helper untuk INSERT, UPDATE, DELETE
    public static int exec(String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = prepareStatement(sql, params)) {
            return stmt.executeUpdate();
        }
    }

    // Helper untuk menyiapkan PreparedStatement dengan parameter
    private static PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }

    public static void migrate() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS `rooms` (
              `id` int(11) NOT NULL AUTO_INCREMENT,
              `code` varchar(10) NOT NULL,
              `name` varchar(100) NOT NULL,
              `area_in_m2` double NOT NULL DEFAULT 0,
              `capacity` int(11) NOT NULL DEFAULT 0,
              `rent_price_per_hour` decimal(18,2) NOT NULL DEFAULT 0.00,
              `note` text DEFAULT NULL,
              PRIMARY KEY (`id`)
            ) ENGINE=InnoDB;
            """;
        try {
            exec(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
