package org.example.Entities;
import org.example.Enums.RoomType;

public class Room {
    private int roomNumber;
    private RoomType roomType;
    private int pricePerNight;

    public Room(int roomNumber, RoomType roomType, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void update(RoomType type, int price) {
        this.roomType = type;
        this.pricePerNight = price;
    }

    @Override
    public String toString() {
        return "Room{number=" + roomNumber +
                ", type=" + roomType +
                ", pricePerNight=" + pricePerNight + "}";
    }
}
