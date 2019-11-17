package AviaService.Services;

import AviaService.Booking_DB;
import AviaService.Entities.Booking;

import java.util.List;

public class BookingService  {

    private Booking_DB booking_db = new Booking_DB();

    public List<Booking> getAllBookings() { return booking_db.getAllBookings(); }

    public void myBookings(String name, String surname) { booking_db.myBookings(name, surname); }

    public List<Booking> addBooking() {
        return booking_db.addBooking();
    }

    public List<Booking> cancelBooking(String bookId) {
        return booking_db.cancelBooking(bookId);
    }

    public Booking findById(String bookId) { return booking_db.findById(bookId); }
}
