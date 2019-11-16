package AviaService.Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable {

    private String passenger;
    private ArrayList<String> passengers;
    private Flight flight;
    private String id;

    public Booking(String id, Flight flight, ArrayList<String> passengers) {
        this.id = id;
        this.flight = flight;
        this.passengers = passengers;
    }

    public Booking (String id) {
        this.id = id;
    }

    public Booking (String name, String surname) {
        this.passenger = name + surname;
    }

    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getId() {
        return id;
    }

    public String getPassenger() {
        return passenger;
    }
}
