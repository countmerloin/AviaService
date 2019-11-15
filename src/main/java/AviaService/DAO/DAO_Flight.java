package AviaService.DAO;

import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;
import java.time.LocalDateTime;
import java.util.List;

public interface DAO_Flight  <T>{
    List<FlightsTable> getAllFlights();
    Flight getFlightById(String id);
    List<Flight> getFlightByInfo(String destination, LocalDateTime date, int passenger);
    void saveFlight(Flight flight);
    boolean deleteFlight();
}