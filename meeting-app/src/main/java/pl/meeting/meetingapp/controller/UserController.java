package pl.meeting.meetingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pl.meeting.meetingapp.api.UserApi;
import pl.meeting.meetingapp.models.*;
import pl.meeting.meetingapp.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController implements UserApi{

    private final UserService userService;

    @Override
    public ResponseEntity<Void> addUser(UserPostModelApi userPostModelApi) {
        UserRegisteredModelApi newUser = userService.addUser(userPostModelApi);

        String newResourceUrl = "/users/" + newUser.getId();

        return ResponseEntity.created(URI.create(newResourceUrl)).build();
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
    public ResponseEntity<Void> updateUserProfile(Long userId, ProfilePatchModelApi body) {
        userService.updateUserProfile(userId, body);

        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<List<UserGetModelApi>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserGetModelApi> getUserById(Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProfileGetModelApi> getUserProfile(Long id) {
        return ResponseEntity.ok()
                .body(userService.getUserProfile(id));
    }



}


