package AviaService.Services;

import AviaService.Booking_DB;
import AviaService.Entities.Booking;

import java.util.List;

public class BookingService  {

    private Booking_DB booking_db = new Booking_DB();

    public void myBookings(String username) {
        booking_db.myBookings(username);
    }

    public List<Booking> addBooking() {
        return booking_db.addBooking();
    }

    public List<Booking> cancelBooking(String bookId) {
        return booking_db.cancelBooking(bookId);
    }
}
