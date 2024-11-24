package mk.finki.ukim.mk.lab.repository;

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
                1L,
                "City Convention Center",
                "500 Convention Blvd, San Francisco, CA 94105",
                "1000",
                "A modern, large-scale venue ideal for trade shows, expos, and corporate events."
        ));

        locationList.add(new Location(
                2L,
                "Lakeside Banquet Hall",
                "200 Lakeview Dr, Seattle, WA 98101",
                "350",
                "An elegant lakeside venue perfect for weddings, receptions, and special celebrations."
        ));

        locationList.add(new Location(
                3L,
                "Tech Innovation Hub",
                "1200 Silicon Valley Dr, Palo Alto, CA 94304",
                "200",
                "A cutting-edge venue designed for tech conferences, meetups, and innovation workshops."
        ));

        locationList.add(new Location(
                4L,
                "Royal Ballroom",
                "450 King St, Toronto, ON M5V 3N8",
                "600",
                "A luxurious ballroom for grand events such as galas, award ceremonies, and corporate dinners."
        ));

        locationList.add(new Location(
                5L,
                "Sequoia Conference Center",
                "1800 Redwood Rd, Sacramento, CA 95814",
                "400",
                "Nestled among towering redwoods, this venue is ideal for outdoor conferences and team-building activities."
        ));


    }
    public List<Location> findAll(){
        return locationList;
    }

    public  Location getLocationById(Long id){
        return locationList.stream().filter(location -> location.getId().equals(id)).findFirst().orElse(null);
    }
    public  void AddLocation(Location location){
        if(getLocationById(location.getId()) != null){
            locationList.add(location);
        }
    }
}
