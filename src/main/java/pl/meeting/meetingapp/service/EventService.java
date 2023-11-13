package pl.meeting.meetingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.meeting.meetingapp.Exception.Event.EventNotFoundException;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.entity.Location;
import pl.meeting.meetingapp.entity.LocationRepository;
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
    private final LocationRepository locationRepository;

    public List<EventModelApi> getEvents() {
        return eventRepository.
                findAll()
                .stream()
                .map(eventMapper::mapToEventModelApi)
                .collect(Collectors.toList());
    }

    public EventGetModelApi createEvent(EventPostModelApi eventPostModelApi) {

//        Location location = locationRepository.findById(eventPostModelApi.getLocationId())
//                .orElseThrow(RuntimeException::new);

        Location location = new Location();
        location.setId(1L);
        location.setName("Test name");
        location.setAddress("Cwiartki 3/4");
        location.setLatitude(10.12);
        location.setLongitude(16.22);
        locationRepository.save(location);

        Event event = eventMapper.mapToEvent(eventPostModelApi);
        event.setLocation(location);

        return eventMapper.mapToEventGetModelApi(eventRepository.save(event));
    }

    public EventGetModelApi getEvent(Long eventId) {
        return eventMapper.mapToEventGetModelApi(eventRepository.getEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId)));
    }



}
