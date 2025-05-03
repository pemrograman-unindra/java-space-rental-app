package unindra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;

public class DBConnection {
    private static Connection conn;

    private static String getEnv(Dotenv dotenv, String key, String defaultValue) {
        if (dotenv != null) {
            String value = dotenv.get(key);
            if (value != null) return value;
        }
        String envValue = System.getenv(key);
        return envValue != null ? envValue : defaultValue;
    }

    public static void openConnection() throws SQLException {
        Dotenv dotenv = null;
        try {
            File envFile = new File(".env");
            if (envFile.exists()) {
                dotenv = Dotenv.configure()
                        .directory(".")
                        .ignoreIfMalformed()
                        .ignoreIfMissing()
                        .load();
            }
        } catch (Exception e) {
            // Tidak perlu lempar error, cukup abaikan dan pakai default
        }
        String dbHost = getEnv(dotenv, "DB_HOST", "localhost");
        String dbPort = getEnv(dotenv, "DB_PORT", "3306");
        String dbName = getEnv(dotenv, "DB_NAME", "space_rental");
        String dbUser = getEnv(dotenv, "DB_USERNAME", "root");
        String dbPassword = getEnv(dotenv, "DB_PASSWORD", "");
        conn = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName, dbUser, dbPassword);
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

}
