package AviaService;

import AviaService.Entities.Booking;
import AviaService.Entities.BookingTable;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Booking_DBTest {
    private BookingTable bt;
    private List<Booking> bookDB;


    @Before
    public void before() {
        bt = new BookingTable();
        bookDB = bt.loadBookDB();
    }

    @Test
    public void getAllBookings() {
    }

    @Test
    public void myBookings() {
        List<Booking> newList = bookDB;
        Booking booking = new Booking("Anar", "Ismayilov");
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

//            if (i == 0) {
//                System.out.println("Bookings for this person not found.");
//            }
        } System.out.println(i);
   }

    @Test
    public void addBooking() {
    }

    @Test
    public void findById() {
//        for (Booking b : bookDB) {
//            if (b.getId().equalsIgnoreCase(bookId)) {
//                System.out.println(b);
//            }
    }


    @Test
    public void cancelBooking() {
        String s1= "AA A";
        String s2 = "aa a";
        if (s1.equalsIgnoreCase(s2)){
            System.out.println("true");
        }
    }
}