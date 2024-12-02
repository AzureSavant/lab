package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    Optional<Event> getEventById(Long id);
    List<Event> searchEventsByName(String text);
    List<Event> searchEventsBypopularityScore(double score);
    Event createEvent(Event event);
    Event editEvent(Long Id, Event requestEvent);

    void deleteEvent(Long eventId);
}
