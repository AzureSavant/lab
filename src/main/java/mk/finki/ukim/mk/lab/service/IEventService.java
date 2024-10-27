package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;

public interface IEventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> searchEventsByName(String text);
    List<Event> searchEventsBypopularityScore(double score);
}
