package AviaService.Controllers;

import AviaService.DAO.DAO_Booking;
import AviaService.Entities.Booking;
import AviaService.Entities.Flight;

import java.util.LinkedList;
import java.util.List;

public class Booking_DB implements DAO_Booking {
    List<Booking> allBookings = new LinkedList<>();

    @Override
    public List<Booking> myBookings(String username) {
        return null;
    }

    @Override
    public void addBooking(String username) {

    }

    @Override
    public void cancelBooking(String username) {

    }
}
