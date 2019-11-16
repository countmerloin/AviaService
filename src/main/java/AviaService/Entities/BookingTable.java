package AviaService.Entities;

import AviaService.Flight_DB;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingTable implements Serializable {
    private Flight_DB flight_db = new Flight_DB();

    public Booking createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight ID");
        String idFlight = scanner.nextLine();
        Flight flight = flight_db.getFlightById(idFlight);

        System.out.println("Enter ticket count:");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> passengers = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            System.out.println("Please enter name and surname #:" + (i+1));
            String passenger = scanner.nextLine();
            passengers.add(passenger);
        }

        String idBook = flight.getId().charAt(2) + String.valueOf(flight.getId().charAt(4)) +
                            passengers.get(0).charAt(4) + passengers.get(0).charAt(3);

        return new Booking(idBook, flight, passengers);
    }

    private boolean isExist() {
        File file = new File("src/test/java/AviaService/BooksTable.ser");
        return file.exists();
    }

    private void creatBookDB() {
        File file = new File("src/test/java/AviaService/BooksTable.ser");
    }

    public void loadBookDB() throws FileNotFoundException {
        if (isExist()) {
            FileInputStream fileIn =
                    new FileInputStream("src/test/java/AviaService/BooksTable.ser");
        } else creatBookDB();
    }

}
