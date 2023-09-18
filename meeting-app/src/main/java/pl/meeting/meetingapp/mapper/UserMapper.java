package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.models.UserGetModelApi;
import pl.meeting.meetingapp.models.UserModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserRegisteredModelApi mapToUserRegisteredModelApi(User user);

    UserModelApi mapToUserModelApi(User user);

    UserGetModelApi mapToUserGetModelApi(User user);



}
