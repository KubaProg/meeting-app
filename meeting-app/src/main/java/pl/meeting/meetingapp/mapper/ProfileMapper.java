package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.models.ProfileModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {

    ProfileModelApi mapToProfileModelApi(Profile profile);

    default Long mapEventToLong(Event event) {
        if (event == null) {
            return null;
        }
        return event.getId(); // Assuming getId returns a Long representing the Event ID
    }


}