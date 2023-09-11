package pl.meeting.meetingapp.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.models.EventModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    EventModelApi mapToEventModelApi(Event event);

}
