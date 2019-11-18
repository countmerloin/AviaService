package AviaService.Controllers;

import AviaService.Services.BookingService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BookingControllerTest {
    private BookingService bookService;
    @Before
    public void before(){
        bookService = new BookingService();
    }
    @Test
    public void getAllBookings() {
        System.out.println(Arrays.toString(bookService.getAllBookings().toArray()));
    }

    @Test
    public void myBookings() {
        String name = "tahir";
        String surname = "Dov";
        bookService.myBookings(name, surname);
    }

    @Test
    public void addBooking() {
        bookService.addBooking();
    }

    @Test
    public void cancelBooking() {
        bookService.cancelBooking("kpek1954");
    }

    @Test
    public void findById() {
        bookService.findById("kosl1875");
    }
}