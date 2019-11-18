package AviaService.Controllers;

import AviaService.Services.FlightService;
import org.junit.Before;
import org.junit.Test;


public class FlightControllerTest {
    private FlightService fs;

    @Before
    public void before() {
        fs  = new FlightService();
    }

    @Test
    public void getAllFlights() {
        System.out.println(fs.getAllFlights());
        //not recommended to run unless you check it for the first time
        //it will print 1000 flights
    }

    @Test
    public void getFlightById() {
        //checked. It works with Database and database is dynamic, so might cause errors if you run it again
    }

    @Test
    public void getFlightByInfo() {
        //checked. It works with Database and database is dynamic, so might cause errors if you run it again
    }

    @Test
    public void saveFlight() {
        //checked. Don't run, otherwise it will affect Database
    }

    @Test
    public void getFlights24() {
        fs.getFlights24();
    }
}