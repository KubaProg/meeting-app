package pl.meeting.meetingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.meeting.meetingapp.dto.EventDto.EventGetDto;
import pl.meeting.meetingapp.mapper.EventMapper;
import pl.meeting.meetingapp.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public List<EventGetDto> getEvents() {
        return eventRepository.
                findAll()
                .stream()
                .map(eventMapper::mapToEventGetDto)
                .collect(Collectors.toList());
    }

}
