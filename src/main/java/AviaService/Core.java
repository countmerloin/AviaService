package AviaService;

import AviaService.Controllers.BookingController;
import AviaService.Controllers.FlightController;
import AviaService.Controllers.MenuController;
import AviaService.Entities.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class Core {
    private final Console console;
    private final Menu menu;
    private final FlightsTable flightsTable;
    private final BookingTable bookingTable;
    private final ChoosingMenu parser;
    private final BookingController bookingController;
    private final FlightController flightController;
    private final MenuController menuController;

    Core(Console console, FlightsTable flightsTable, BookingTable bookingTable) {
        this.console = console;
        this.flightsTable = flightsTable;
        this.bookingTable = bookingTable;
        this.menu = new Menu();
        this.parser = new ChoosingMenu();
        this.flightController = new FlightController();
        this.bookingController = new BookingController();
        this.menuController = new MenuController();
    }

    void run() {
        if (flightsTable.isExisted()) {
            flightsTable.loadDBF();
        } else flightsTable.createDBF();

        if (bookingTable.isExist()) {
            bookingTable.loadBookDB();
        } else bookingTable.creatBookDB();

        boolean cont = true;
        console.printLn(menu.show());
        while (cont) {
            String line = console.readLn();
            MenuPoints user_input = parser.parse(line);
            switch (user_input) {
                case ONLINE_BOARD:
                    flightController.getFlights24();
                    console.printLn("");
                    console.printLn(menu.show());
                    menuController.help();
                    break;
                case FLIGHT_INFO: {
                    console.printLn("Enter flight ID: ");
                    String flightID = console.readLn();
                    System.out.println(flightController.getFlightById(flightID));
                    console.printLn("");
                    console.printLn(menu.show());
                    menuController.help();
                }
                break;

                case BOOKING: {
                    console.printLn("Please, enter destination (capital of European country):");
                    String dest = console.readLn();

                    console.printLn("Please, enter date in the following format yyyy-MM-dd:");
                    LocalDate dateInput = LocalDate.parse(console.readLn());
                    LocalDateTime date = dateInput.atStartOfDay();

                    console.printLn("Please, enter passenger count:");
                    int passenger = Integer.parseInt(console.readLn());

                    List<Flight> flights = flightController.getFlightByInfo(dest, date, passenger);
                    for (Flight f : flights) {
                        System.out.println(f);
                    }
                    if (flightController.getFlightByInfo(dest, date, passenger).size() == 0) {
                        System.out.println("Flight not found. Please, search again.");
                        console.printLn(menu.show());
                        menuController.help();
                    } else {
                        console.printLn("Choose action: 1. Make a book");
                        console.printLn("               2. Return to menu");
                        int choose = Integer.parseInt(console.readLn());
                        boolean flag = true;
                        while (flag) {
                            switch (choose) {
                                case 1: {
                                    bookingController.addBooking();
                                    int index = bookingController.getAllBookings().size() - 1;

                                    Flight flight = bookingController.getAllBookings().get(index).getFlight();
                                    int ticket = bookingController.getAllBookings().get(index).getPassengers().size();
                                    flight.setPassengerCount(flight.getPassengerCount() - ticket);
                                    flightController.saveFlight(flight);
                                    console.printLn("Booking is saved! Your booking ID: " +
                                            bookingController.getAllBookings().get(index).getId());
                                    console.printLn("");
                                    console.printLn(menu.show());
                                    menuController.help();
                                    flag = false;
                                }
                                break;
                                default:
                                    console.printLn("");
                                    console.printLn(menu.show());
                                    menuController.help();
                                    flag = false;
                            }
                        }
                    }
                }
                break;

                case CANCEL_BOOKING: {
                    console.printLn("Please enter Booking ID:");
                    String bookId = console.readLn();
                    Optional<Booking> optBook = Optional.ofNullable(bookingController.findById(bookId));
                    Booking book;
                    if (optBook.isPresent()){
                        book = optBook.get();}
                    else {
                        System.out.println("Booking not found!");
                        console.printLn("");
                        console.printLn(menu.show());
                        menuController.help();
                        break;
                    }
                    int tickets = book.getPassengers().size();
                    int index = bookingController.getAllBookings().indexOf(book);
                    Flight flight = bookingController.getAllBookings().get(index).getFlight();
                    flight.setPassengerCount(flight.getPassengerCount() + tickets);
                    flightController.saveFlight(flight);
                    bookingController.cancelBooking(bookId);
                    console.printLn("");
                    console.printLn(menu.show());
                    menuController.help();
                }
                break;

                case MY_BOOKINGS: {
                    console.printLn("Enter your name:");
                    String name = console.readLn();
                    console.printLn("Enter your surname:");
                    String surname = console.readLn();

                    bookingController.myBookings(name, surname);
                    console.printLn("");
                    console.printLn(menu.show());
                    menuController.help();
                }
                break;

                case EXIT: {
                    flightsTable.updateDBF(flightController.getAllFlights());
                    bookingTable.updateBookDB(bookingController.getAllBookings());
                    cont = false;
                }
                break;

                case REPEAT:
                default:
                    console.printLn("");
                    console.printLn(menu.show());
                    menuController.help();
                    break;
            }
        }
    }
}
