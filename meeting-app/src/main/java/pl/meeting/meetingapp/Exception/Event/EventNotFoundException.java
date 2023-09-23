package pl.meeting.meetingapp.Exception.Event;

import org.springframework.http.HttpStatus;
import pl.meeting.meetingapp.Exception.BusinessException;

public class EventNotFoundException extends BusinessException {
    public EventNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Event with if of %d does not exist", id));}

}
