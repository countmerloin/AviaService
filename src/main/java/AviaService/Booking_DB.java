package AviaService;

import AviaService.DAO.DAO_Booking;
import AviaService.Entities.Booking;
import AviaService.Entities.BookingTable;

import java.util.List;

public class Booking_DB implements DAO_Booking {
    private BookingTable bt = new BookingTable();
    private List<Booking> bookDB = bt.loadBookDB();
    private BookingTable bookTable = new BookingTable();

    private List<Booking> getAllBookings() {
        return bookDB;
    }

    @Override
    public void myBookings(String username) {
        List<Booking> newList = getAllBookings();
        Booking booking = new Booking(username);
        for (Booking b : newList
        ) {
            for (String s : b.getPassengers()
            ) {
                s = s.trim();
                if (s.equalsIgnoreCase(booking.getPassenger())) System.out.println(b);
                else System.out.println("Bookings for this person not found.");
            }
        }
    }

    @Override
    public List<Booking> addBooking() {
        bookDB.add(bookTable.createBook());
        return bookDB;
    }

    @Override
    public List<Booking> cancelBooking(String bookId) {
        for (Booking b : bookDB) {
            if (b.getId().equalsIgnoreCase(bookId)) {
                bookDB.remove(b);
            }
        }
        return bookDB;
    }
}
