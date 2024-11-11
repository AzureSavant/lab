package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class EventController {
    @Autowired
    private IEventService eventService;
 //TODO: resolve issue with multiple search params ex. "summer 8.5"
    @GetMapping("/events")
    public String getEventsPage(@RequestParam(required = false) String search, Model model){
        List<Event> events = eventService.listAll();
        if (search != null && !search.isEmpty()) {
            events = eventService.searchEventsByName(search);
            try {
                events.addAll(eventService.searchEventsBypopularityScore(Double.parseDouble(search)));
            }
            catch (Exception e){}
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
    @PostMapping("/save")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam String locationId){

        Event event = new Event();
        if(!name.isEmpty() && !description.isEmpty() && !locationId.isEmpty()){
            event = new Event(name,description,popularityScore,new Location(Long.parseLong(locationId)));
            Event result = eventService.createEvent(event);
            if(result != null){
                return "redirect:/events";
            }
        }
        return "redirect:/error";
    }
    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }
}
