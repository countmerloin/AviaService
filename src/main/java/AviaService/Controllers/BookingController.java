package AviaService.Controllers;

import AviaService.Entities.Booking;
import AviaService.Services.BookingService;

import java.util.List;

public class BookingController {

   private BookingService bookService = new BookingService();

    public void myBookings(String username) {
        bookService.myBookings(username);
    }

    public List<Booking> addBooking() {
        return bookService.addBooking();
    }

    public List<Booking> cancelBooking(String bookId) {
        return bookService.cancelBooking(bookId);
    }
}
