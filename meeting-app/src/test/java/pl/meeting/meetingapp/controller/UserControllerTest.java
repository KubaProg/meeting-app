package pl.meeting.meetingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.meeting.meetingapp.MeetingAppApplication;
import pl.meeting.meetingapp.models.UserLoginModelApi;
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;
import pl.meeting.meetingapp.repository.UserRepository;
import pl.meeting.meetingapp.service.UserService;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingAppApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;
    UserPostModelApi userPostModelApi;
    UserLoginModelApi userLoginModelApi;
    @BeforeAll
    static void beforeAll(){

    }

    @AfterAll
    static void afterAll(){

    }

    @BeforeEach
    void initializeData(){
        userPostModelApi = new UserPostModelApi()
                .username("user1@usermail.com")
                .password("hardpassword")
                .firstName("name")
                .surname("surname")
                .phoneNumber("123456789");


    }

    @AfterEach
    void cleanData(){
        userRepository.deleteAll();
    }


    @Test
    @Transactional
    void addUser_DataToAddUserGiven_ShouldAddNewUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/add")
                        .content(objectMapper.writeValueAsString(userPostModelApi))
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", Matchers.equalTo(userPostModelApi.getUsername())))
                .andExpect(jsonPath("$.password", Matchers.notNullValue()));
    }

    @Test
    @Transactional
    void logInByCredentials_DataToAddUserGiven_ShouldAddNewUser() throws Exception{

        UserRegisteredModelApi userRegisteredModelApi = userService.addUser(userPostModelApi);

        userLoginModelApi = new UserLoginModelApi()
                .username(userPostModelApi.getUsername())
                .password(userPostModelApi.getPassword());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .content(objectMapper.writeValueAsString(userLoginModelApi))
                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", Matchers.equalTo(userPostModelApi.getUsername())))
                .andExpect(jsonPath("$.jwtToken", Matchers.notNullValue()));
    }

}