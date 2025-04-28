package unindra.dao;

import unindra.model.Room;
import unindra.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void createRoom(Room room) {
        String query = "INSERT INTO rooms (code, name, area_in_m2, capacity, rent_price_per_hour, note) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, room.getCode());
            stmt.setString(2, room.getName());
            stmt.setDouble(3, room.getAreaInM2());
            stmt.setInt(4, room.getCapacity());
            stmt.setDouble(5, room.getRentPricePerHour());
            stmt.setString(6, room.getNote());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
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
        String query = "UPDATE rooms SET code = ?, name = ?, area_in_m2 = ?, capacity = ?, rent_price_per_hour = ?, note = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, room.getCode());
            stmt.setString(2, room.getName());
            stmt.setDouble(3, room.getAreaInM2());
            stmt.setInt(4, room.getCapacity());
            stmt.setDouble(5, room.getRentPricePerHour());
            stmt.setString(6, room.getNote());
            stmt.setInt(7, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int id) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
