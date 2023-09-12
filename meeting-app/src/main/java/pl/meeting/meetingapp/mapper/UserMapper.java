package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.dto.UserDto.UserLoginDto;
import pl.meeting.meetingapp.dto.UserDto.UserPostDto;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.models.UserLoginModelApi;
import pl.meeting.meetingapp.models.UserModelApi;
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {



    UserPostDto mapToUserPostDto(UserPostModelApi userPostModelApi);

    UserPostModelApi mapToUserPostModelApi(UserModelApi userModelApi);

    UserLoginModelApi mapToUserLoginModelApi(User user);


    UserPostModelApi mapToUserPostModelApi(User user);


    UserRegisteredModelApi mapToUserRegisteredModelApi(User user);

    UserModelApi mapToUserModelApi(User user);

    UserLoginDto mapToUserLoginDto(UserLoginModelApi userLoginModelApi);


}
