package AviaService.DAO;

import AviaService.Entities.Booking;

import java.util.List;

public interface DAO_Booking<T> {
    void myBookings(String name, String surname);

    List<Booking> addBooking();

    Booking findById(String bookId);

    List<Booking> cancelBooking(String bookId);
}
