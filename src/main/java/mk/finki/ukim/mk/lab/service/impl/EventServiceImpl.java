package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.IEventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return eventRepository.findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(text,text);
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> searchEventsByName(String text) {
        return eventRepository.findAllByName(text);
    }

    @Override
    public List<Event> searchEventsBypopularityScore(double score) {
        return eventRepository.findAllByPopularityScore(score);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event editEvent(Long Id, Event requestEvent) {
        Optional<Event> originalEvent = getEventById(Id);
        if(originalEvent.isPresent()){
            requestEvent.setName( (!requestEvent.getName().isEmpty() && (requestEvent.getName() != null))
                    ? requestEvent.getName() : originalEvent.get().getName());
            requestEvent.setDescription( (!requestEvent.getDescription().isEmpty() && (requestEvent.getDescription() != null))
                    ? requestEvent.getDescription() : originalEvent.get().getDescription());
            requestEvent.setPopularityScore( (!Double.isNaN(requestEvent.getPopularityScore()))
                    ? requestEvent.getPopularityScore() : originalEvent.get().getPopularityScore());
            requestEvent.setLocation((requestEvent.getLocation().getId() != null)
                    ? requestEvent.getLocation() : originalEvent.get().getLocation());
            requestEvent = eventRepository.save(requestEvent);
        }
        return originalEvent.orElse(null);
    }

    @Override
    public void deleteEvent(Long eventId) {
         eventRepository.deleteById(eventId);
    }
}
