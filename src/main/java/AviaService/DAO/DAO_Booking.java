package AviaService.DAO;

import AviaService.Entities.Booking;

import java.util.List;

public interface DAO_Booking <T>{
    List<Booking> myBookings(String username);
    void addBooking(String username);
    void cancelBooking(String username);
}
