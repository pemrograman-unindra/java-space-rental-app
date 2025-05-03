package unindra;

import unindra.db.DBConnection;
import unindra.db.DBMigration;
import unindra.ui.RoomList;

public class SpaceRentalApp {

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
