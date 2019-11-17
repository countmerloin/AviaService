package AviaService.Entities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FlightsTable implements Serializable {
    Cities c = new Cities();
    private List<Flight> flightsTable = new ArrayList<>();

    public FlightsTable() {
        if (isExisted()) {
            loadDBF();
        } else {
            createDBF();
        }
    }

    public boolean isExisted() {
        File file = new File("src/test/java/AviaService/FlightsTable.ser");
        return file.exists();
    }

    public List<Flight> createFlights() {
        List<String> cities = c.getCityName();
        for (int i = 0; i < 1000; i++) {
            String city = cities.get((int) (Math.random() * 21));
            LocalDateTime flightDate = LocalDateTime.now().plusSeconds((long) (Math.random() * 2592000))
                    .truncatedTo(ChronoUnit.HOURS);

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

    public void createDBF() {
        createFlights();
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/test/java/AviaService/FlightsTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(flightsTable);
        } catch (IOException e) {
            System.out.println("smth went wrong during flights file filling");
        }
    }

    public List<Flight> loadDBF() {
        List<Flight> flightsTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/test/java/AviaService/FlightsTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            flightsTable = (List<Flight>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
        return flightsTable;
    }
}

