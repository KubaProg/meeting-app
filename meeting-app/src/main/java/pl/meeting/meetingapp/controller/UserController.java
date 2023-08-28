package pl.meeting.meetingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.meeting.meetingapp.mapper.UserMapper;
import pl.meeting.meetingapp.service.UserService;
import pl.meeting.meetingapp.api.UserApi;
import pl.meeting.meetingapp.models.UserModelApi;
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;

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


