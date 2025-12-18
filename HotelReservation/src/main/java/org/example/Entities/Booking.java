package org.example.Entities;
import org.example.Enums.RoomType;
import java.util.Date;

public class Booking {
    private int userId;
    private int roomNumber;
    private RoomType roomTypeAtBooking;
    private int pricePerNightAtBooking;
    private Date checkIn;
    private Date checkOut;
    private int totalPrice;

    public Booking(User user, Room room, Date checkIn, Date checkOut, int nights) {
        this.userId = user.getUserId();
        this.roomNumber = room.getRoomNumber();
        this.roomTypeAtBooking = room.getRoomType();
        this.pricePerNightAtBooking = room.getPricePerNight();
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = nights * pricePerNightAtBooking;
    }

    public boolean overlaps(Date start, Date end) {
        return start.before(checkOut) && end.after(checkIn);
    }

    @Override
    public String toString() {
        return "Booking{userId=" + userId +
                ", roomNumber=" + roomNumber +
                ", type=" + roomTypeAtBooking +
                ", pricePerNight=" + pricePerNightAtBooking +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalPrice=" + totalPrice + "}";
    }
}
