package AviaService;


import AviaService.Controllers.BookingController;
import AviaService.Controllers.FlightController;
import AviaService.Controllers.MenuController;
import AviaService.Entities.BookingTable;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;


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
                    break;
                case FLIGHT_INFO: {
                    System.out.println("Enter flight ID: ");
                    String flightID = console.readLn();
                    System.out.println(flightController.getFlightById(flightID));
                }
                break;
                case BOOKING: {
                    System.out.println("Please, enter destination:");
                    String dest = console.readLn();
                    System.out.println("Please, enter date:");
                    LocalDateTime date = LocalDateTime.now();  //Edit this line
                    System.out.println("Please, enter passenger count:");
                    int passenger = Integer.parseInt(console.readLn());

                    List<Flight> flights = flightController.getFlightByInfo(dest, date, passenger);

                    for (Flight f : flights) {
                        System.out.println(f);
                    }

                    bookingController.addBooking();
                    //Update flight list
                }
                break;

                case CANCEL_BOOKING: {
                    System.out.println("Please enter Booking ID:");
                    String bookId = console.readLn();
                    bookingController.cancelBooking(bookId);
                    //Update Flight list
                }
                break;
                case MY_BOOKINGS: {
                    System.out.println("Enter your name:");
                    String name = console.readLn();
                    System.out.println("Enter your surname:");
                    String surname = console.readLn();

                    String fullName = name + surname;
                    bookingController.myBookings(fullName);
                }
                break;
                case EXIT:
                    //update databases
                    cont = false;
                    break;
                case REPEAT:
                default:
                    console.printLn(menuController.help());
                    break;
            }
        }
    }
}
