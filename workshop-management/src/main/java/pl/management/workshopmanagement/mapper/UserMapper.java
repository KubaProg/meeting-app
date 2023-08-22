package pl.management.workshopmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.management.workshopmanagement.dto.UserGetDto;
import pl.management.workshopmanagement.models.UserModelApi;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserModelApi mapToUserModelApi(UserGetDto userGetDto);

}
