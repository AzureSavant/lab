package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.IEventService;
import mk.finki.ukim.mk.lab.service.ILocationService;
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
    @Autowired
    private ILocationService locationService;
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
    @PostMapping("/events/add")
    public String saveEvent(@RequestParam (required = false) Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam String locationId){

        Event event;
        if(!name.isEmpty() && !description.isEmpty() && !locationId.isEmpty()){
            event = new Event(name,description,popularityScore,new Location(Long.parseLong(locationId)));
            Event result;
            if(id != null){
                 result = eventService.editEvent(id,event);
            }
            else {
                 result = eventService.createEvent(event);
            }
            if(result != null){
                return "redirect:/events";
            }
        }
        return "redirect:/error";
    }
    @GetMapping("/events/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId, Model model){
        if(eventService.getEventById(eventId) != null) {
            model.addAttribute("event", eventService.getEventById(eventId));
            model.addAttribute("locations", locationService.findAll());
            return "add-event";
        }
        return "redirect:/error";
    }
    @GetMapping("/events/add-form")
    public String getAddEventPage( Model model){
            model.addAttribute("locations", locationService.findAll());
            return "add-event";
    }

    @PostMapping("/events/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId){
        eventService.deleteEvent(eventId);

            return "redirect:/events";

    }

    @GetMapping("/error")
    public String errorPage(){
        return "errorPage";
    }
}
