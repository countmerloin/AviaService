package AviaService;

import AviaService.DAO.DAO_Flight;
import AviaService.Entities.Cities;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Flight_DB implements DAO_Flight {
    Cities c = new Cities();
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
        List<Flight> searchRes = new LinkedList<>();
        int i = 0;
        for (Flight f : dbf) {
            if (search.hashCode() == f.hashCode() && search.equals(f) && passenger <= f.getPassengers()) {
                System.out.println(f);
                searchRes.add(f);
                i++;
            }
        }
        if (i == 0) System.out.println("Flight not found. Please, search again.");
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
    public List<Flight> deleteFlight() {
        int i = 0;
        for (Flight f : getAllFlights()) {
            if (f.getDate().isBefore(LocalDateTime.now())) {
                dbf.remove(f);
                i++;
            }
        }
        List<String> cities = c.getCityName();
        for (int j = 0; j < i; j++) {
            String city = cities.get((int) (Math.random() * 21));
            LocalDateTime flightDate = LocalDateTime.now().plusSeconds((long) (Math.random() * 2592000))
                    .truncatedTo(ChronoUnit.HOURS);

            char[] partOfId = new char[3];
            city.getChars(0, 3, partOfId, 0);
            String id = "K" + partOfId[0] + partOfId[1] + partOfId[2] + flightDate.getHour();

            Flight f = new Flight(id, city, flightDate);
            dbf.add(f);
        }
        dbf.sort((flight, f1) -> (flight.getDate().isAfter(f1.getDate()) ? -1 :
                (flight.getDate().equals(f1.getDate()) ? 0 : 1)));
        return dbf;
    }

    @Override
    public void getFlights24() {
        LocalDateTime in24hour = LocalDateTime.now().plusHours(24);
        for (Flight f : getAllFlights()) {
            if (f.getDate().isBefore(in24hour) && f.getDate().isAfter(LocalDateTime.now())) {
                System.out.println(f);
            }
        }
    }
}



