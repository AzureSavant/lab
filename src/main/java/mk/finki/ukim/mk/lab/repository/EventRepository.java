package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public static List<Event> eventList;
    public final LocationRepository locationRepository;

    public EventRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;

        eventList = new ArrayList<>(10);
        eventList.add(new Event("Summer Music Festival", "An annual festival featuring local bands and food vendors.", 8.5, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Tech Conference 2024", "A gathering of industry leaders to discuss the latest innovations in technology.", 9.2, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Art in the Park", "A community event showcasing local artists and their work.", 7.8, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Annual Charity Run", "A fun run to raise funds for local charities and promote fitness.", 8.0, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Winter Wonderland", "A festive celebration with ice skating, holiday markets, and light displays.", 9.0, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Farmers' Market", "A weekly market featuring fresh produce and handmade goods from local vendors.", 8.3, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Film Festival", "A week-long event showcasing independent films and documentaries.", 8.7, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Local Sports Day", "A day of friendly competitions and games for all ages.", 7.5, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Cultural Heritage Fair", "A celebration of diverse cultures with food, performances, and workshops.", 8.9, this.locationRepository.getLocationById(1L)));
        eventList.add(new Event("Outdoor Adventure Expo", "An exhibition featuring outdoor gear, activities, and adventure travel opportunities.", 8.1, this.locationRepository.getLocationById(1L)));
    }

    public List<Event> findAll() {
        return eventList;
    }

    public List<Event> searchEvents(String text) {
        return eventList.stream().filter(e -> e.getName().toLowerCase().contains(text.toLowerCase()) || e.getDescription().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public List<Event> searchEventsByName(String text) {
        return eventList.stream().filter(e -> e.getName().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public List<Event> searchEventsBypopularityScore(double score) {
        return eventList.stream().filter(e -> e.getPopularityScore() >= score).collect(Collectors.toList());
    }

    public Event getEventById(Long Id) {
        return eventList.stream().filter(e -> e.getId().equals(Id)).findFirst().orElse(null);
    }

    public Event saveEvent(Event event) {
        Location location = locationRepository.getLocationById(event.getLocation().getId());
        event.setLocation(location);
        eventList.add(event);
        return event;
    }

    public Event editEvent(Event event) {
        Event eventToEdit = getEventById(event.getId());
        if (eventToEdit != null) {
            Location location = locationRepository.getLocationById(event.getLocation().getId());
            event.setLocation(location);
            eventList.remove(eventToEdit);
            eventList.add(event);
        }
        return event;
    }

    public Event deleteEvent(Long Id) {
        Event event = getEventById(Id);
        int index = eventList.indexOf(event);

        if (index != -1 && event != null) {
            return eventList.remove(index);
        }
        return null;
    }
}
