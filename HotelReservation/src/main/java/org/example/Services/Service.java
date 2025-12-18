package org.example.Services;
import org.example.Entities.Booking;
import org.example.Entities.Room;
import org.example.Entities.User;
import org.example.Enums.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Service {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    // ---------------- USERS ----------------
    public void setUser(int userId, int balance) {
        if (findUser(userId) != null) return;
        users.add(new User(userId, balance));
    }

    // ---------------- ROOMS ----------------
    public void setRoom(int roomNumber, RoomType roomType, int pricePerNight) {
        Room room = findRoom(roomNumber);
        if (room == null) {
            rooms.add(new Room(roomNumber, roomType, pricePerNight));
        } else {
            room.update(roomType, pricePerNight);
        }
    }

    // ---------------- BOOKING ----------------
    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        if (!checkIn.before(checkOut)) {
            throw new IllegalArgumentException("Invalid dates");
        }

        User user = findUser(userId);
        Room room = findRoom(roomNumber);

        if (user == null || room == null) {
            throw new IllegalArgumentException("User or Room not found");
        }

        for (Booking b : bookings) {
            if (b.overlaps(checkIn, checkOut) && b.toString().contains("roomNumber=" + roomNumber)) {
                throw new IllegalStateException("Room already booked");
            }
        }

        long diff = checkOut.getTime() - checkIn.getTime();
        int nights = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int totalPrice = nights * room.getPricePerNight();

        if (user.getBalance() < totalPrice) {
            throw new IllegalStateException("Insufficient balance");
        }

        user.debit(totalPrice);
        bookings.add(new Booking(user, room, checkIn, checkOut, nights));
    }

    // ---------------- PRINT ----------------
    public void printAll() {
        System.out.println("=== ROOMS ===");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            System.out.println(rooms.get(i));
        }

        System.out.println("=== BOOKINGS ===");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            System.out.println(bookings.get(i));
        }
    }

    public void printAllUsers() {
        for (int i = users.size() - 1; i >= 0; i--) {
            System.out.println(users.get(i));
        }
    }

    // ---------------- HELPERS ----------------
    private User findUser(int id) {
        return users.stream().filter(u -> u.getUserId() == id).findFirst().orElse(null);
    }

    private Room findRoom(int number) {
        return rooms.stream().filter(r -> r.getRoomNumber() == number).findFirst().orElse(null);
    }
}
