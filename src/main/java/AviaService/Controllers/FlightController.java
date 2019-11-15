package AviaService.Controllers;

import AviaService.Entities.Flight;
import AviaService.Services.FlightService;

import java.time.LocalDateTime;
import java.util.List;

public class FlightController {

    FlightService fs = new FlightService();

    public List<Flight> getAllFlights() {
        return fs.getAllFlights();
    }

    public Flight getFlightById(String id) {
        return fs.getFlightById(id);
    }

    public List<Flight> getFlightByInfo(String destination, LocalDateTime date, int passenger) {
        return fs.getFlightByInfo(destination, date, passenger);
    }

    public void saveFlight(Flight flight) {
        fs.saveFlight(flight);
    }

    public boolean deleteFlight() {
        return fs.deleteFlight();
    }

    public List<Flight> getFlights24() {
        return fs.getFlights24();
    }
}
