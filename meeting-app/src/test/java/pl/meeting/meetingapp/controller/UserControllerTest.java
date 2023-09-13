package pl.meeting.meetingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.repository.UserRepository;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingAppApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @BeforeAll
    static void beforeAll(){

    }

    @AfterAll
    static void afterAll(){

    }


    @Test
    @Transactional
    void addUser_DataToAddUserGiven_ShouldAddNewUser() throws Exception {

        UserPostModelApi userPostModelApi = new UserPostModelApi()
                .username("user1@usermail.com")
                .password("hardpassword")
                .firstName("name")
                .surname("surname")
                .phoneNumber("123456789");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/add")
                        .content(objectMapper.writeValueAsString(userPostModelApi))
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$").isNotEmpty())
//                .andExpect(jsonPath("$.category", Matchers.equalTo(category.getCategory())))
//                .andExpect(jsonPath("$.id", Matchers.equalTo(category.getId().intValue())));
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