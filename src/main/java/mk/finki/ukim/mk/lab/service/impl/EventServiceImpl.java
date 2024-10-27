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
    public List<Event> searchEventsByName(String text) {
        return eventRepository.searchEventsByName(text);
    }

    @Override
    public List<Event> searchEventsBypopularityScore(double score) {
        return eventRepository.searchEventsBypopularityScore(score);
    }
}
