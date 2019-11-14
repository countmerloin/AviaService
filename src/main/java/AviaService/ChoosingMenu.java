package AviaService;

public class ChoosingMenu {
    public MenuPoints parse(String input) {
        if ("1".equals(input)) return MenuPoints.ONLINE_BOARD;
        else if ("2".equals(input)) return MenuPoints.FLIGHT_INFO;
        else if ("3".equals(input)) return MenuPoints.BOOKING;
        else if ("4".equals(input)) return MenuPoints.CANCEL_BOOKING;
        else if ("5".equals(input)) return MenuPoints.MY_BOOKINGS;
        else if ("EXIT".equalsIgnoreCase(input)) return MenuPoints.EXIT;
        else return MenuPoints.REPEAT;
    }
}
