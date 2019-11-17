package AviaService;


import AviaService.Controllers.BookingController;
import AviaService.Controllers.FlightController;
import AviaService.Controllers.MenuController;
import AviaService.Entities.Booking;
import AviaService.Entities.BookingTable;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Core {

    private final Console console;
    private final Menu menu;
    private final FlightsTable flightsTable;
    private final BookingTable bookingTable;
    private final ChoosingMenu parser;
    private final BookingController bookingController;
    private final FlightController flightController;
    private final MenuController menuController;

    public Core(Console console, FlightsTable flightsTable, BookingTable bookingTable) {
        this.console = console;
        this.flightsTable = flightsTable;
        this.bookingTable = bookingTable;
        this.menu = new Menu();
        this.parser = new ChoosingMenu();
        this.flightController = new FlightController();
        this.bookingController = new BookingController();
        this.menuController = new MenuController();
    }

    public void run() {
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
                    menuController.help();
                    break;
                case FLIGHT_INFO: {
                    System.out.println("Enter flight ID: ");
                    String flightID = console.readLn();
                    System.out.println(flightController.getFlightById(flightID));
                    menuController.help();
                }
                break;
                case BOOKING: {
                    System.out.println("Please, enter destination:");
                    String dest = console.readLn();
                    System.out.println("Please, enter date in the following format yyyy-MM-dd:");
                    LocalDate dateInput = LocalDate.parse(console.readLn());
                    LocalDateTime date = dateInput.atStartOfDay();

                    System.out.println("Please, enter passenger count:");
                    int passenger = Integer.parseInt(console.readLn());

                    List<Flight> flights = flightController.getFlightByInfo(dest, date, passenger);
                    for (Flight f : flights) {
                        System.out.println(f);
                    }
                    System.out.println("Choose action: 1. Make a book");
                    System.out.println("               2. Return to menu");
                    int choose = Integer.parseInt(console.readLn());
                    boolean flag = true;
                    while (flag) {
                        switch (choose) {
                            case 1: {
                                bookingController.addBooking();
                                int index = bookingController.getAllBookings().size() - 1;

                                Flight flight = bookingController.getAllBookings().get(index).getFlight();
                                int ticket = bookingController.getAllBookings().get(index).getPassengers().size();
                                flight.setPassengers(flight.getPassengers() - ticket);
                                flightController.saveFlight(flight);
                                System.out.println("Booking is saved!");
                                menuController.help();
                                flag=false;
                            }
                            break;
                            default:
                                menuController.help();
                                flag=false;
                        }
                    }
                }
                break;

                case CANCEL_BOOKING: {
                    System.out.println("Please enter Booking ID:");
                    String bookId = console.readLn();
                    Booking book = bookingController.findById(bookId);
                    int tickets = book.getPassengers().size();
                    int index = bookingController.getAllBookings().indexOf(book);
                    Flight flight = bookingController.getAllBookings().get(index).getFlight();
                    flight.setPassengers(flight.getPassengers() + tickets);
                    flightController.saveFlight(flight);
                    bookingController.cancelBooking(bookId);
                    menuController.help();
                }
                break;

                case MY_BOOKINGS: {
                    System.out.println("Enter your name:");
                    String name = console.readLn();
                    System.out.println("Enter your surname:");
                    String surname = console.readLn();

                    bookingController.myBookings(name, surname);
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
                    console.printLn(menuController.help());
                    break;
            }
        }
    }
}
