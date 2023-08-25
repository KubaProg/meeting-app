package pl.management.workshopmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.management.workshopmanagement.dto.UserGetDto;
import pl.management.workshopmanagement.dto.UserPostDto;
import pl.management.workshopmanagement.entity.User;
import pl.management.workshopmanagement.models.UserModelApi;
import pl.management.workshopmanagement.models.UserPostModelApi;
import pl.management.workshopmanagement.models.UserRegisteredModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserModelApi mapToUserModelApi(UserGetDto userGetDto);

    UserGetDto mapToUserGetDto(User user);

    UserPostDto mapToUserPostDto(UserPostModelApi userPostModelApi);

    UserRegisteredModelApi mapToUserRegisteredModelApi(UserGetDto userGetDto);

}
