package AviaService.Entities;

import java.util.Arrays;
import java.util.List;

public class Cities {
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
    List<String> getCityName(){ return cities;}
}
