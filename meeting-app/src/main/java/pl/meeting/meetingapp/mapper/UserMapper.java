package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.dto.UserGetDto;
import pl.meeting.meetingapp.dto.UserLoginDto;
import pl.meeting.meetingapp.dto.UserPostDto;
import pl.meeting.meetingapp.dto.UserPutDto;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.models.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserModelApi mapToUserModelApi(UserGetDto userGetDto);

    UserGetDto mapToUserGetDto(User user);

    UserPostDto mapToUserPostDto(UserPostModelApi userPostModelApi);

    UserRegisteredModelApi mapToUserRegisteredModelApi(UserGetDto userGetDto);

    UserPutDto mapToUserPutDto(UserPutModelApi userPutModelApi);

    UserLoginDto mapToUserLoginDto(UserLoginModelApi userLoginModelApi);
}
