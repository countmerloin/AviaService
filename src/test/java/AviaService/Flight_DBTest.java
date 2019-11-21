package AviaService;

import AviaService.Entities.Cities;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Flight_DBTest {
    private String id;
    private Cities c;
    private FlightsTable ft;
    private List<Flight> dbf;

    @Before
    public void before() {
        c = new Cities();
        ft = new FlightsTable();
        List<Flight> flightsTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/main/java/AviaService/Datas/FlightsTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            flightsTable = (ArrayList<Flight>) in.readObject();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        dbf = flightsTable;

        id = dbf.get(500).getId();
    }

    @Test
    public void getAllFlights() {
        assertNotNull(dbf.get(450));
    }

    @Test
    public void getAllFlights2() {
        assertNotNull(dbf.get(999));
    }


    @Test
    public void getFlightById() {
        Flight flight = dbf.get(500);
        assertEquals(flight.getId(), id);
    }

    @Test
    public void getFlightByInfo() {
        String city = "Vladivostok";
        LocalDateTime time = LocalDateTime.now();
        int passengerCount = 5;

        Flight flight = new Flight(city, time, passengerCount);
        assertNotEquals(dbf.get(7), flight);
    }

    @Test
    public void saveFlight() {
        String city = "Vladivostok";
        LocalDateTime time = LocalDateTime.now();
        int passengerCount = 5;
        Flight flight = new Flight(city, time, passengerCount);
        dbf.add(flight);
        assertEquals(dbf.size(), 1001);
    }

    @Test
    public void deleteFlight() {
        dbf.remove(500);
        assertNotEquals(dbf.size(), 9000);
    }

    @Test
    public void getFlights24() {
    }

}