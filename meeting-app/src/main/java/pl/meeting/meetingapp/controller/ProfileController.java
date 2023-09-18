package pl.meeting.meetingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.meeting.meetingapp.api.ProfileApi;
import pl.meeting.meetingapp.models.ProfileGetModelApi;
import pl.meeting.meetingapp.models.ProfileModelApi;
import pl.meeting.meetingapp.service.UserService;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {

    private final UserService userService;

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
