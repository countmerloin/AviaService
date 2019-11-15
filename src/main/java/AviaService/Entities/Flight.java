package AviaService.Entities;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight implements Serializable {
    private String id;
    private String destination;
    private LocalDateTime date;
    private int passengers = 5;

    public Flight(String id, String destination, LocalDateTime date) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.passengers =passengers;
    }

    public Flight(String destination, LocalDateTime date, int passengers) {

        this.destination = destination;
        this.date = date;
        this.passengers =passengers;
    }

    public Flight(String id) {
        this.id = id;
    }

    public Flight(LocalDateTime date) {
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getPassengers() {
        return passengers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return getId().equals(flight.getId()) &&
                Objects.equals(getDestination(), flight.getDestination()) &&
                Objects.equals(getDate(), flight.getDate());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getDestination(), getDate());
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Flight ID: %s, from: Kiev, to: %s, on: %s", id, destination,
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}


