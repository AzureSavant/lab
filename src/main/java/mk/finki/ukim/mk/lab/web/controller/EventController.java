package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class EventController {
    @Autowired
    private IEventService eventService;

    @GetMapping("/")
    public String Index(@RequestParam(required = false) String search, Model model){
        List<Event> events = eventService.listAll();
        if (search != null && !search.isEmpty()) {
            events = eventService.searchEventsByName(search);
            events.addAll(eventService.searchEventsBypopularityScore(Double.parseDouble(search)));
        }
        model.addAttribute("events", events);
        return "listEvents";
    }
    @PostMapping("/eventBooking")
    public String bookEvent(String selectedEvent, String numTickets,Model model){
        var event = eventService.searchEvents(selectedEvent).stream().findFirst();
        model.addAttribute("event", event.orElse(null));
        model.addAttribute("numTickets", numTickets);
        return "bookingConfirmation";
    }
}
