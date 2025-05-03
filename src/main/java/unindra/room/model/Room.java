package unindra.room.model;

public class Room {
    private int id;
    private String code;
    private String name;
    private double areaInM2;
    private int capacity;
    private double rentPricePerHour;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAreaInM2() {
        return areaInM2;
    }

    public void setAreaInM2(double areaInM2) {
        this.areaInM2 = areaInM2;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getRentPricePerHour() {
        return rentPricePerHour;
    }

    public void setRentPricePerHour(double rentPricePerHour) {
        this.rentPricePerHour = rentPricePerHour;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
