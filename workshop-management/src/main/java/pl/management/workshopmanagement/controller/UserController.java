package pl.management.workshopmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.management.workshopmanagement.api.UserApi;
import pl.management.workshopmanagement.mapper.UserMapper;
import pl.management.workshopmanagement.models.UserModelApi;
import pl.management.workshopmanagement.models.UserPostModelApi;
import pl.management.workshopmanagement.models.UserRegisteredModelApi;
import pl.management.workshopmanagement.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserMapper userMapper;
    private final UserService userService;


    @Override
    public ResponseEntity<UserRegisteredModelApi> addUser(UserPostModelApi userPostModelApi) {
        return ResponseEntity.ok(userMapper.mapToUserRegisteredModelApi(
                userService.addUser(userMapper.mapToUserPostDto(userPostModelApi))
        ));
    }

    @Override
    public ResponseEntity<List<UserModelApi>> getAllUsers() {

        return ResponseEntity.ok()
                .body(userService.getAllUsers()
                        .stream()
                        .map(userMapper::mapToUserModelApi)
                        .collect(Collectors.toList())
                );
    }

}


