package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationRepository {
    public static List<Location> locationList;

    public LocationRepository() {
        locationList = new ArrayList<>(10);

        locationList.add(new Location(
                "City Convention Center",
                "500 Convention Blvd, San Francisco, CA 94105",
                "1000",
                "A modern, large-scale venue ideal for trade shows, expos, and corporate events."
        ));

        locationList.add(new Location(
                "Lakeside Banquet Hall",
                "200 Lakeview Dr, Seattle, WA 98101",
                "350",
                "An elegant lakeside venue perfect for weddings, receptions, and special celebrations."
        ));

        locationList.add(new Location(
                "Tech Innovation Hub",
                "1200 Silicon Valley Dr, Palo Alto, CA 94304",
                "200",
                "A cutting-edge venue designed for tech conferences, meetups, and innovation workshops."
        ));

        locationList.add(new Location(
                "Royal Ballroom",
                "450 King St, Toronto, ON M5V 3N8",
                "600",
                "A luxurious ballroom for grand events such as galas, award ceremonies, and corporate dinners."
        ));

        locationList.add(new Location(
                "Sequoia Conference Center",
                "1800 Redwood Rd, Sacramento, CA 95814",
                "400",
                "Nestled among towering redwoods, this venue is ideal for outdoor conferences and team-building activities."
        ));


    }
    public List<Location> findAll(){
        return locationList;
    }
}
