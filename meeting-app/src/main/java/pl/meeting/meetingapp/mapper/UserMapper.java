package pl.meeting.meetingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.meeting.meetingapp.dto.UserDto.UserGetDto;
import pl.meeting.meetingapp.dto.UserDto.UserLoginDto;
import pl.meeting.meetingapp.dto.UserDto.UserPostDto;
import pl.meeting.meetingapp.dto.UserDto.UserPutDto;
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
