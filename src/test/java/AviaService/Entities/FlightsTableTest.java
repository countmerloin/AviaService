package AviaService.Entities;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightsTableTest {

    Cities c;
    private List<Flight> flightsTable;

    @Before
    public void before() {
        c = new Cities();
        flightsTable = new ArrayList<>();
    }

    @Test
    public void createFlights() {
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
        flightsTable.sort((flight, f1) -> (flight.getDate().isBefore(f1.getDate()) ? -1 :
                (flight.getDate().equals(f1.getDate()) ? 0 : 1)));
        for (int i = 0; i < 10; i++) {
            System.out.println(flightsTable.get(i));

        }
    }

    @Test
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


    @Test
    public void loadDBF() {
        List<Flight> flightsTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/test/java/AviaService/FlightsTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            flightsTable = (List<Flight>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(flightsTable.get(i));
        }
    }

    @Test
    public void deleteFlights() {
        List<Flight> dbf = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/test/java/AviaService/FlightsTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dbf = (List<Flight>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(dbf.get(i));

        }
        System.out.println("");
        int i = 0;
        for (int k=0; k<dbf.size(); k++){
               Flight f =  dbf.get(k);
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


        for (int j = 0; j < 10; j++) {
            System.out.println(dbf.get(j));
        }
    }

    @Test
    public void updateDBF() {

        List<Flight> flights = new ArrayList<>();
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/test/java/AviaService/FlightsTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(flights);
        } catch (IOException e) {
            System.out.println("smth went wrong during flights file filling");
        }
    }
}