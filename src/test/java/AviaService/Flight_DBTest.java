package AviaService;

import AviaService.DAO.DAO_Flight;
import AviaService.Entities.Cities;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Flight_DBTest  {

    Cities c;
    FlightsTable ft;
    private List<Flight> dbf;

    @Before
    public void before() {
        c = new Cities();
        ft = new FlightsTable();
        List<Flight> flightsTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/test/java/AviaService/FlightsTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            flightsTable = (ArrayList<Flight>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
        dbf = flightsTable;
    }
    @Test
    public void getAllFlights() {
        System.out.println(dbf.get(5));
    }

    @Test
    public void getFlightById() {
    }

    @Test
    public void getFlightByInfo() {
    }

    @Test
    public void saveFlight() {
    }

    @Test
    public void deleteFlight() {
    }

    @Test
    public void getFlights24() {
    }
}