package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.dto.ProfileDto.ProfileGetDto;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.models.ProfileModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {

    ProfileModelApi mapToProfileModelApi(Profile profile);

    ProfileGetDto mapToProfileGetDto(Profile profile);
}
