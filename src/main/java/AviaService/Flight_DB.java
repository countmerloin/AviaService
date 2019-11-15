package AviaService;

import AviaService.DAO.DAO_Flight;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Flight_DB implements DAO_Flight {
    FlightsTable ft = new FlightsTable();
    private List<Flight> dbf = ft.loadDBF();

    @Override
    public List<Flight> getAllFlights() {
        return dbf;
    }

    @Override
    public Flight getFlightById(String id) {
        List<Flight> dbf = getAllFlights();
        Flight flight = new Flight(id);
        for (Flight f : dbf) {
            if (id.equals(f.getId()))
                flight = f;
        }
        return flight;
    }

    @Override
    public List<Flight> getFlightByInfo(String destination, LocalDateTime date, int passenger) {
        Flight search = new Flight(destination, date, passenger);
        dbf = getAllFlights();
        List<Flight> searchRes = new LinkedList();
        for (Flight f : dbf) {
            if (search.hashCode() != f.hashCode() && search.equals(f) && passenger > f.getPassengers()) {
                search = null;
            } else {
                searchRes.add(search);
            }
        }
        return searchRes;
    }

    @Override
    public void saveFlight(Flight flight) {
        if (!dbf.contains(flight)) {
            dbf.add(flight);
        } else {
            dbf.set(dbf.indexOf(flight), flight);
        }
    }

    @Override
    public boolean deleteFlight() {
        boolean deleted = false;
        for (Flight f : getAllFlights()) {
            if (f.getDate().isBefore(LocalDateTime.now())) {
                dbf.remove(f);
                deleted = true;
            }
        }
        return deleted;
    }
}


