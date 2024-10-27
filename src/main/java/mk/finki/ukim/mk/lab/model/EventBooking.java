package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventBooking {

    protected String eventName;
    protected String attendeeName;
    protected String attendeeAddress;
    protected Long numberOfTickets;

}
