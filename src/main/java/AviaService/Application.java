package AviaService;

import AviaService.Entities.BookingTable;
import AviaService.Entities.FlightsTable;

public class Application {
    public static void main(String[] args) {
        Console console = new SystemConsole();
        FlightsTable ft = new FlightsTable();
        BookingTable bt = new BookingTable();
        Core app = new Core(console, ft, bt);
        app.run();
    }
}
