package pl.meeting.meetingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.meeting.meetingapp.Exception.Event.EventNotFoundException;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.mapper.EventMapper;
import pl.meeting.meetingapp.models.EventGetModelApi;
import pl.meeting.meetingapp.models.EventModelApi;
import pl.meeting.meetingapp.models.EventPostModelApi;
import pl.meeting.meetingapp.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public List<EventModelApi> getEvents() {
        return eventRepository.
                findAll()
                .stream()
                .map(eventMapper::mapToEventModelApi)
                .collect(Collectors.toList());
    }

    public Event createEvent(EventPostModelApi eventPostModelApi) {
        Event savedEvent = eventRepository.save(eventMapper.mapToEvent(eventPostModelApi));

        return savedEvent;
    }

    public EventGetModelApi getEvent(Long eventId) {
        return eventMapper.mapToEventGetModelApi(eventRepository.getEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId)));
    }



}
