package AviaService;

import AviaService.Entities.Booking;
import AviaService.Entities.BookingTable;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Booking_DBTest {
    private Booking_DB bd;
    private BookingTable bt;
    private List<Booking> bookDB;


    @Before
    public void before() {
        bd  = new Booking_DB();
        bt = new BookingTable();
        bookDB = bt.loadBookDB();
        bookDB.add(null);
    }

    @Test
    public void getAllBookings() {
        assertNull(bookDB.get(0));
    }

    @Test
    public void myBookings() {

   }

    @Test
    public void addBooking() {
        Booking booking = new Booking("Anar", "Ismayilov");
        bookDB.add(booking);
        assertNotNull(bookDB.get(1));
    }

    @Test
    public void findById() {

    }


    @Test
    public void cancelBooking() {
        Booking booking = new Booking("Tahir", "Dovtalabi");
        bookDB.add(booking);
        String id = bookDB.get(1).getId();
        bd.cancelBooking(id);
        assertEquals(bookDB.size(), 2);
    }
}