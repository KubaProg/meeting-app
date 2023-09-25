package pl.meeting.meetingapp.mapper;

import org.mapstruct.*;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.models.ProfileGetModelApi;
import pl.meeting.meetingapp.models.ProfilePutModelApi;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {

    default List<Long> mapEventsToLongs(List<Event> events) {
        if (events == null) {
            return null;
        }
        return events.stream()
                .map(Event::getId)
                .collect(Collectors.toList());
    }

    default List<Event> mapLongsToEvents(List<Long> eventIds) {
        if (eventIds == null) {
            return null;
        }
        return eventIds.stream()
                .map(id -> {
                    Event event = new Event();
                    event.setId(id);
                    return event;
                })
                .collect(Collectors.toList());
    }

    ProfileGetModelApi mapToProfileGetModelApi(Profile profile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromProfilePatchModelApi(ProfilePutModelApi dto, @MappingTarget Profile entity);


}