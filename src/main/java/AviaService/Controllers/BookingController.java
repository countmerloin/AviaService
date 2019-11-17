package AviaService.Controllers;

import AviaService.Entities.Booking;
import AviaService.Services.BookingService;

import java.util.List;

public class BookingController {

   private BookingService bookService = new BookingService();

    public List<Booking> getAllBookings() {
        return bookService.getAllBookings();
    }

    public void myBookings(String name, String surname) {
        bookService.myBookings(name, surname);
    }

    public List<Booking> addBooking() {
        return bookService.addBooking();
    }

    public List<Booking> cancelBooking(String bookId) { return bookService.cancelBooking(bookId); }

    public Booking findById(String bookId) { return bookService.findById(bookId); }
}
