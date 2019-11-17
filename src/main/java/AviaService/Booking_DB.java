package AviaService;

import AviaService.DAO.DAO_Booking;
import AviaService.Entities.Booking;
import AviaService.Entities.BookingTable;

import java.util.List;
import java.util.Optional;

public class Booking_DB implements DAO_Booking {
    private BookingTable bt = new BookingTable();
    private List<Booking> bookDB = bt.loadBookDB();


    public List<Booking> getAllBookings() {
        return bookDB;
    }

    @Override
    public void myBookings(String name, String surname) {
        List<Booking> newList = bookDB;
        Booking booking = new Booking(name, surname);
        int i = 0;
        for (Booking b : newList
        ) {
            for (String s : b.getPassengers()
            ) {
                s = s.replaceAll("\\s+", "");
                if (s.equalsIgnoreCase(booking.getPassenger())) {
                    System.out.println(b);
                    i++;
                }
            }
            if (i == 0) {
                System.out.println("Bookings for this person not found.");
            }
        }
    }

    @Override
    public List<Booking> addBooking() {
        bookDB.add(bt.createBook());
        return bookDB;
    }

    @Override
    public Booking findById(String bookId) {
        Booking x = null;
        for (Booking b : bookDB) {
            if (b.getId().equalsIgnoreCase(bookId)) {
                x = b;
            }
        }
        return x;
    }


    @Override
    public List<Booking> cancelBooking(String bookId) {
        for (int i =0; i< bookDB.size(); i++) {
            Booking b = bookDB.get(i);
            if (b.getId().equalsIgnoreCase(bookId)) {
                bookDB.remove(b);
            }
        }
        return bookDB;
    }
}
