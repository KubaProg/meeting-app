package pl.meeting.meetingapp.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.models.EventGetModelApi;
import pl.meeting.meetingapp.models.EventModelApi;
import pl.meeting.meetingapp.models.EventPostModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    EventModelApi mapToEventModelApi(Event event);

    EventGetModelApi mapToEventGetModelApi(Event event);

    Event mapToEvent(EventPostModelApi eventPostModelApi);

}
