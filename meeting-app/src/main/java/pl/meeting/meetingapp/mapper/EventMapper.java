package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.dto.EventDto.EventGetDto;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.models.EventModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    EventGetDto mapToEventGetDto(Event event);

    EventModelApi mapToEventModelApi(EventGetDto eventGetDto);


}
