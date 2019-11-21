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
            deleteFlights();
        } else {
            createDBF();
        }
    }

    public boolean isExisted()
    {
        File file = new File("src/main/java/AviaService/Datas/FlightsTable.ser");
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
            int random = (int)(Math.random()*99);
            String id = "K" + partOfId[0] + partOfId[1] + partOfId[2] + flightDate.getDayOfMonth() + random;

            Flight f = new Flight(id, city, flightDate);
            flightsTable.add(f);
        }
        flightsTable.sort((flight, f1) -> (flight.getDate().isBefore(f1.getDate()) ? -1 :
                (flight.getDate().equals(f1.getDate()) ? 0 : 1)));
        return flightsTable;
    }

    public void createDBF() {
        createFlights();
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/java/AviaService/Datas/FlightsTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(flightsTable);
        } catch (IOException e) {
            System.out.println("smth went wrong during flights file filling");
        }
    }

    public void updateDBF(List<Flight> flights) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/java/AviaService/Datas/FlightsTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(flights);
        } catch (IOException e) {
            System.out.println("smth went wrong during flights file filling");
        }
    }

    public List<Flight> loadDBF() {
        List<Flight> flightsTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/main/java/AviaService/Datas/FlightsTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            flightsTable = (ArrayList<Flight>) in.readObject();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        return flightsTable;
    }

    public List<Flight> deleteFlights() {
        List<Flight> dbf = new ArrayList<>();
        int i = 0;
        for (Flight f : loadDBF()) {
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
        dbf.sort((flight, f1) -> (flight.getDate().isBefore(f1.getDate()) ? -1 :
                (flight.getDate().equals(f1.getDate()) ? 0 : 1)));
        return dbf;
    }

}

