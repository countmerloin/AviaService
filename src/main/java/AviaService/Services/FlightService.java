package AviaService.Services;


import AviaService.Entities.Flight;
import AviaService.Flight_DB;

import java.time.LocalDateTime;
import java.util.List;

public class FlightService {

    private Flight_DB services = new Flight_DB();

    public List<Flight> getAllFlights() {
        return services.getAllFlights();
    }

    public Flight getFlightById(String id) {
        return services.getFlightById(id);
    }

    public List<Flight> getFlightByInfo(String destination, LocalDateTime date, int passenger) {
        return services.getFlightByInfo(destination, date, passenger);
    }

    public void saveFlight(Flight flight) {
        services.saveFlight(flight);
    }

    public void getFlights24() {
        services.getFlights24();}
}
