package AviaService.Entities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FlightsTable implements Iterable<Flight>, Serializable {
    private final List<String> cities = Arrays.asList("Baku",
            "Minsk",
            "Riga",
            "Vilnius",
            "Astana",
            "Tbilisi",
            "Warsaw",
            "Prague",
            "Vienna",
            "Amsterdam",
            "London",
            "Saint-Petersburg",
            "Madrid",
            "Lisbon",
            "Athens",
            "Budapest",
            "Belgrade",
            "Stockholm",
            "Oslo",
            "Glasgow",
            "Berlin",
            "Kiev");

    private List<Flight> flightsTable = new ArrayList<>();

    public FlightsTable() {
        if (isExisted()) {
            loadDBF();
        } else {
            createDBF();

        }
    }

    public boolean isExisted() {
        return false;
    }


    public List<Flight> createFlights() {
        for (int i = 0; i < 1000; i++) {

            int index = (int) (Math.random() * 21);
            String city = cities.get(index);

            long after = (long) (Math.random() * 2592000);
            LocalDateTime flightDate = LocalDateTime.now().plusSeconds(after).truncatedTo(ChronoUnit.HOURS);

            char[] partOfId = new char[3];
            city.getChars(0, 3, partOfId, 0);
            String id = "K" + partOfId[0] + partOfId[1] + partOfId[2] + flightDate.getHour();

            Flight f = new Flight(id, city, flightDate);
            flightsTable.add(f);
        }
        flightsTable.sort(new Comparator<Flight>() {
            @Override
            public int compare(Flight flight, Flight f1) {
                return (flight.getDate().isAfter(f1.getDate()) ? -1 :
                        (flight.getDate().equals(f1.getDate()) ? 0 : 1));
            }
        });
        return flightsTable;
    }


    @Override
    public Iterator<Flight> iterator() {
        return flightsTable.iterator();
    }

    public void createDBF() {
        createFlights();
        try {FileOutputStream fileOut =
                new FileOutputStream("src/main/java/AviaService/datas/FlightsTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            flightsTable.forEach(flight -> {
                try {
                    out.writeObject(flight);

                } catch (IOException e) {
                    System.out.println("smth went wrong during flights file filling");
                }
            });
        } catch (IOException e) {
            System.out.println("smth went wrong during flights file creation");
        }
    }

    public List<Flight> loadDBF() {

            try {FileInputStream fileIn =
                    new FileInputStream("src/main/java/AviaService/datas/FlightsTable.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Flight f = (Flight) in.readObject();
                flightsTable.add(f);
            } catch (ClassNotFoundException | IOException e) {

            }


    return flightsTable;}

}

