package AviaService.Services;

import AviaService.DAO.DAO_Flight;
import AviaService.Entities.Flight;
import AviaService.Flight_DB;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FlightService {
    //DAO_Flight changed to Flight_DB

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

    public List<Flight> deleteFlight() {
        return services.deleteFlight();
    }


    public void getFlights24() {
        LocalDateTime in24hour = LocalDateTime.now().plusHours(24);
        Flight flight = new Flight(in24hour);
        for (Flight f : getAllFlights()) {
            if (f.getDate().isBefore(in24hour) && f.getDate().isAfter(LocalDateTime.now())) {
                System.out.println(f);
            }
        }
    }

}
