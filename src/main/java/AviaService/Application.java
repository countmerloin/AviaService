package AviaService;

import AviaService.Controllers.FlightController;
import AviaService.Entities.Flight;
import AviaService.Entities.FlightsTable;

public class Application {
    public static void main(String[] args) {
        FlightsTable ft = new FlightsTable();
        FlightController fc = new FlightController();
//        Menu menu = new Menu();
//        System.out.println(menu.show());
        ft.createFlights();
        ft.createDBF();
        ft.loadDBF();
        System.out.println(fc.getFlights24());



    }
}
