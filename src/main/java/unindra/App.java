package unindra;

import unindra.core.db.DBConnection;
import unindra.core.db.DBMigration;
import unindra.room.ui.RoomList;

public class App {

    public static void main(String[] args) {
        try {
            DBConnection.openConnection();
            DBMigration.migrate();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                DBConnection.closeConnection();
            }));
            javax.swing.SwingUtilities.invokeLater(() -> {
                new RoomList().setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
