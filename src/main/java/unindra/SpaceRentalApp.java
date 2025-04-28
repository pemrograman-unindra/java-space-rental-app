package unindra;

import unindra.ui.RoomList;

public class SpaceRentalApp {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new RoomList().setVisible(true);
        });
    }
}
