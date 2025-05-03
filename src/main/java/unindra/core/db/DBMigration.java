package unindra.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBMigration {
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
            Connection connection = DBConnection.getConnection(); 
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
