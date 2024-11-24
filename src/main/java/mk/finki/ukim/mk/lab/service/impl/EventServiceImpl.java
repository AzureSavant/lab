package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.IEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements IEventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public List<Event> searchEventsByName(String text) {
        return eventRepository.searchEventsByName(text);
    }

    @Override
    public List<Event> searchEventsBypopularityScore(double score) {
        return eventRepository.searchEventsBypopularityScore(score);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.saveEvent(event);
    }

    @Override
    public Event editEvent(Long Id, Event requestEvent) {
        Event originalEvent = getEventById(Id);
        if(originalEvent != null){
            originalEvent.setName( (!requestEvent.getName().isEmpty() && (requestEvent.getName() != null))
                    ? requestEvent.getName() : originalEvent.getName());
            originalEvent.setDescription( (!requestEvent.getDescription().isEmpty() && (requestEvent.getDescription() != null))
                    ? requestEvent.getDescription() : originalEvent.getDescription());
            originalEvent.setPopularityScore( (!Double.isNaN(requestEvent.getPopularityScore()))
                    ? requestEvent.getPopularityScore() : originalEvent.getPopularityScore());
            originalEvent.setLocation((requestEvent.getLocation().getId() != null)
                    ? requestEvent.getLocation() : originalEvent.getLocation());
            eventRepository.editEvent(originalEvent);
        }
        return originalEvent;
    }

    @Override
    public Event deleteEvent(Long eventId) {
        return eventRepository.deleteEvent(eventId);
    }
}
