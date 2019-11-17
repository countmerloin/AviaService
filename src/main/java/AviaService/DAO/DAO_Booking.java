package AviaService.DAO;

import AviaService.Entities.Booking;

import java.util.List;

public interface DAO_Booking<T> {
    void myBookings(String username);

    List<Booking> addBooking();

    List<Booking> cancelBooking(String bookId);
}
