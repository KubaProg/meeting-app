package pl.meeting.meetingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.meeting.meetingapp.api.UserApi;
import pl.meeting.meetingapp.models.*;
import pl.meeting.meetingapp.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi{

    private final UserService userService;

    @Override
    public ResponseEntity<UserRegisteredModelApi> addUser(UserPostModelApi userPostModelApi) {
        return ResponseEntity.ok(userService.addUser(userPostModelApi));
    }


    @Override
    public ResponseEntity<UserRegisteredModelApi> logInByCredentials(UserLoginModelApi userLoginModelApi) {
        return ResponseEntity.ok()
                .body(userService.logInByCredentials(userLoginModelApi));
    }

    @Override
    public ResponseEntity<UserGetModelApi> putUserById(Long id, UserPutModelApi userPutModelApi) {
        return ResponseEntity.ok()
                .body(userService.putUserById(id, userPutModelApi));
    }

    @Override
    public ResponseEntity<Void> updateUserProfile(Long userId, ProfilePutModelApi body) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserGetModelApi>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProfileByUserId(Long id) {
        userService.deleteUserProfile(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProfileGetModelApi> getUserProfile(Long id) {
        return ResponseEntity.ok()
                .body(userService.getUserProfile(id));
    }



}


