package pl.meeting.meetingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.meeting.meetingapp.dto.UserLoginDto;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.mapper.UserMapper;
import pl.meeting.meetingapp.models.*;
import pl.meeting.meetingapp.repository.UserRepository;
import pl.meeting.meetingapp.service.UserService;
import pl.meeting.meetingapp.api.UserApi;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserMapper userMapper;
    private final UserService userService;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<UserRegisteredModelApi> addUser(UserPostModelApi userPostModelApi) {
        return ResponseEntity.ok(userMapper.mapToUserRegisteredModelApi(
                userService.addUser(userMapper.mapToUserPostDto(userPostModelApi))
        ));
    }

    @Override
    public ResponseEntity<UserRegisteredModelApi> logInByCredentials(UserLoginModelApi userLoginModelApi) {
        return ResponseEntity.ok()
                .body(
                        userMapper.mapToUserRegisteredModelApi(
                        userService.logInByCredentials(userMapper.mapToUserLoginDto(userLoginModelApi)))
                );
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

    @Override
    public ResponseEntity<Void> deleteUserById(Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserModelApi> putUserById(UserPutModelApi userPutModelApi, Long id) {
        return ResponseEntity.ok()
                .body(userMapper.mapToUserModelApi(
                        userService.putUserById(id, userMapper.mapToUserPutDto(userPutModelApi)))
                );
    }




//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
//        try {
//            // Authenticate the user and generate a JWT token
//            String jwtToken = userService.authenticateUser(userLoginDto.getUsername(), userLoginDto.getPassword());
//
//            // Return the JWT token in the response
//            return ResponseEntity.ok(new AuthResponse(jwtToken));
//        } catch (AuthenticationException e) {
//            // Authentication failed, return an error response
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }


}


