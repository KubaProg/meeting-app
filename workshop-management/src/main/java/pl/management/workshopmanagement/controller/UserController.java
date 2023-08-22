package pl.management.workshopmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pl.management.workshopmanagement.api.UserApi;
import pl.management.workshopmanagement.dto.UserGetDto;
import pl.management.workshopmanagement.mapper.UserMapper;
import pl.management.workshopmanagement.models.UserModelApi;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<List<UserModelApi>> getAllUsers() {

        // assuming this comes from service after save
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(123L);
        userGetDto.setUsername("user1@usermail.com");
        userGetDto.setPassword("hardpassword");
        userGetDto.setFirstName("John");
        userGetDto.setSurname("Doe");
        userGetDto.setPhoneNumber("123456789");

        // now we are mapping from DtoToModelApi

        UserModelApi userModelApi = userMapper.mapToUserModelApi(userGetDto);

        return ResponseEntity.ok(List.of(userModelApi));
    }
}
