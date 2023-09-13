package pl.meeting.meetingapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.meeting.meetingapp.MeetingAppApplication;
import pl.meeting.meetingapp.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingAppApplication.class)
class UserControllerTest {

    @Autowired
    UserRepository userRepository;


    @Test
    void addUser() {
    }

    @Test
    void deleteProfileByUserId() {
    }

    @Test
    void logInByCredentials() {
    }

    @Test
    void putUserById() {
    }

    @Test
    void getAllUsers() {

    }

    @Test
    void getUserProfile() {
    }

    @Test
    void deleteUserById() {

    }
}