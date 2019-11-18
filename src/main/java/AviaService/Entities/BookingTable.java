package AviaService.Entities;

import AviaService.Flight_DB;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingTable implements Serializable {
    private List<Booking> book_db = new ArrayList<>();
    private Flight_DB flight_db = new Flight_DB();


    public BookingTable() {
        if (isExist()) loadBookDB();
        else creatBookDB();
    }

    public Booking createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter selected flight ID");
        String idFlight = scanner.nextLine();
        Flight flight = flight_db.getFlightById(idFlight);



        System.out.println("Enter ticket count:");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();
        if (ticketCount > flight.getPassengers()) {
            System.out.println("Please enter valid ticket number:");
            ticketCount = scanner.nextInt();
            scanner.nextLine();
        }
        ArrayList<String> passengers = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            System.out.println("Please enter name and surname #:" + (i + 1));
            String passenger = scanner.nextLine();
            passengers.add(passenger);
        }

        String idBook = flight.getId().charAt(2) + String.valueOf(flight.getId().charAt(4)) +
                flight.getDate().getDayOfYear() + passengers.get(0).charAt(2) + passengers.get(0).charAt(1);
        Booking booking = new Booking(idBook, flight, passengers);

        return booking;
    }

    public boolean isExist() {
        File file = new File("src/main/java/AviaService/Datas/BooksTable.ser");
        return file.exists();
    }

    public void creatBookDB() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/java/AviaService/Datas/BooksTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(book_db);
        } catch (IOException e) {
            System.out.println("smth went wrong during books file filling");
        }
    }

    public void updateBookDB(List<Booking> bookings) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/java/AviaService/Datas/BooksTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(bookings);
        } catch (IOException e) {
            System.out.println("smth went wrong during books file filling");
        }
    }

    public List<Booking> loadBookDB() {
        List<Booking> bookTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/main/java/AviaService/Datas/BooksTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bookTable = (List<Booking>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
        return bookTable;
    }

}
