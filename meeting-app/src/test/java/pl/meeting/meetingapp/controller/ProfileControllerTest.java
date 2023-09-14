package pl.meeting.meetingapp.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.meeting.meetingapp.MeetingAppApplication;
import pl.meeting.meetingapp.models.UserLoginModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingAppApplication.class)
@AutoConfigureMockMvc
class ProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Transactional
    void getUserProfile_UserIdGiven_ShouldReturnUserProfile() throws Exception{

//        UserRegisteredModelApi userRegisteredModelApi = userService.addUser(userPostModelApi);
//
//        userLoginModelApi = new UserLoginModelApi()
//                .username(userPostModelApi.getUsername())
//                .password(userPostModelApi.getPassword());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/v1/auth/login")
//                        .content(objectMapper.writeValueAsString(userLoginModelApi))
//                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username", Matchers.equalTo(userPostModelApi.getUsername())))
//                .andExpect(jsonPath("$.jwtToken", Matchers.notNullValue()));
    }

    @Test
    void deleteProfileByUserId() {
    }

}