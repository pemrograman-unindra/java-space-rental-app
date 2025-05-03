package unindra.room.dao;

import unindra.core.app.DB;
import unindra.room.model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void createRoom(Room room) {
        try {
            DB.exec(
                "INSERT INTO rooms (code, name, area_in_m2, capacity, rent_price_per_hour, note) VALUES (?, ?, ?, ?, ?, ?)",
                room.getCode(),
                room.getName(),
                room.getAreaInM2(),
                room.getCapacity(),
                room.getRentPricePerHour(),
                room.getNote()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try (ResultSet rs = DB.query("SELECT * FROM rooms")) {
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setCode(rs.getString("code"));
                room.setName(rs.getString("name"));
                room.setAreaInM2(rs.getDouble("area_in_m2"));
                room.setCapacity(rs.getInt("capacity"));
                room.setRentPricePerHour(rs.getDouble("rent_price_per_hour"));
                room.setNote(rs.getString("note"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void updateRoom(Room room) {
        try {
            DB.exec(
                "UPDATE rooms SET code = ?, name = ?, area_in_m2 = ?, capacity = ?, rent_price_per_hour = ?, note = ? WHERE id = ?",
                room.getCode(),
                room.getName(),
                room.getAreaInM2(),
                room.getCapacity(),
                room.getRentPricePerHour(),
                room.getNote(),
                room.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int id) {
        try {
            DB.exec("DELETE FROM rooms WHERE id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
