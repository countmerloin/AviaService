package AviaService.Entities;

import AviaService.Flight_DB;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingTable implements Serializable {
    private Flight_DB book_db = new Flight_DB();


    public Booking createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight ID");
        String idFlight = scanner.nextLine();
        Flight flight = book_db.getFlightById(idFlight);

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

    public BookingTable() {
        if (isExist()) loadBookDB();
        else creatBookDB();
    }

    private boolean isExist() {
        File file = new File("src/test/java/AviaService/BooksTable.ser");
        return file.exists();
    }

    private void creatBookDB() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/test/java/AviaService/BooksTable.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(book_db);
        } catch (IOException e) {
            System.out.println("smth went wrong during books file filling");
        }
    }


   public List<Booking> loadBookDB() {
        List<Booking> bookTable = new ArrayList<>();
        try {
            FileInputStream fileIn =
                    new FileInputStream("src/test/java/AviaService/BooksTable.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bookTable = (List<Booking>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
        }
        return bookTable;
    }

}
