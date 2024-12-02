package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {


    List<Event> findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
    List<Event> findAllByName(String text);
    List<Event> findAllByPopularityScore(double score);

    //    List<Event> findAll();
//
//    List<Event> searchEvents(String text);
//
//    List<Event> searchEventsByName(String name);
//
//    List<Event> searchEventsBypopularityScore(double score);
//
//    Event getEventById(Long Id);
//
//    Event saveEvent(Event event);
//
//    Event editEvent(Event event);
//
//    Event deleteEvent(Long Id);
}
