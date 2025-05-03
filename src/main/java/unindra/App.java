package unindra;

import unindra.core.app.DB;
import unindra.room.ui.RoomList;

public class App {

    public static void main(String[] args) {
        try {
            DB.openConnection();
            DB.migrate();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                DB.closeConnection();
            }));
            javax.swing.SwingUtilities.invokeLater(() -> {
                new RoomList().setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
