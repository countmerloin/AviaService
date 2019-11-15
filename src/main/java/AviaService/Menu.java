package AviaService;

public class Menu {
    public String show() {
        StringBuilder menu = new StringBuilder();
        menu.append("|*********************|\n");
        menu.append("|      AviaService    |\n");
        menu.append("|*********************|\n");
        menu.append("| 1. Online Timetable |\n");
        menu.append("| 2. Flight Info      |\n");
        menu.append("| 3. Make a Booking   |\n");
        menu.append("| 4. Cancel Booking   |\n");
        menu.append("| 5. My Bookings      |\n");
        menu.append("|    Exit             |\n");
        menu.append("|*********************|\n");
        return menu.toString();
    }
}
