package AviaService.Entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class FlightsTableTest {

    private final List<String> cities = Arrays.asList("Baku",
            "Minsk",
            "Riga",
            "Vilnius",
            "Astana",
            "Tbilisi",
            "Warsaw",
            "Prague",
            "Vienna",
            "Amsterdam",
            "London",
            "Saint-Petersburg",
            "Madrid",
            "Lisbon",
            "Athens",
            "Budapest",
            "Belgrade",
            "Stockholm",
            "Oslo",
            "Glasgow",
            "Berlin",
            "Kiev");
    private List<Flight> flightsTable = new ArrayList<>();

    @Test
    public void test() {

        for (int i = 0; i < 1000; i++) {

            int index = (int) (Math.random() * 21);
            String city = cities.get(index);

            long after = (long) (Math.random() * 2592000);
            LocalDateTime flightDate = LocalDateTime.now().plusSeconds(after).truncatedTo(ChronoUnit.HOURS);

            char[] partOfId = new char[3];
            city.getChars(0, 3, partOfId, 0);
            String id = "K" + partOfId[0] + partOfId[1] + partOfId[2] + flightDate.getHour();

            Flight f = new Flight(id, city, flightDate);
            flightsTable.add(f);
        }


        }

    }
