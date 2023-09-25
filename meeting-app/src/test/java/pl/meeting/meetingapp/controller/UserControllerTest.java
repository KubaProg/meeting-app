package pl.meeting.meetingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.meeting.meetingapp.ApiRoutes;
import pl.meeting.meetingapp.MeetingAppApplication;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.models.UserLoginModelApi;
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;
import pl.meeting.meetingapp.repository.ProfileRepository;
import pl.meeting.meetingapp.repository.UserRepository;
import pl.meeting.meetingapp.service.UserService;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MeetingAppApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProfileRepository profileRepository;
    ObjectMapper mapper = new ObjectMapper();
    Profile profile;
    UserPostModelApi userPostModelApiToAdd;
    UserPostModelApi userPostModelApiToGetProfile;
    UserRegisteredModelApi userRegisteredModelApi;
    UserLoginModelApi userLoginModelApi;

    @BeforeEach
    void initializeData(){
        userPostModelApiToAdd = new UserPostModelApi()
                .username("user2@gmail.com")
                .password("hardpassword")
                .firstName("name")
                .lastName("surname")
                .phoneNumber("123456789");

        userPostModelApiToGetProfile = new UserPostModelApi()
                .username("user1@usermail.com")
                .password("hardpassword")
                .firstName("name")
                .lastName("surname")
                .phoneNumber("123456789");

        userRegisteredModelApi = userService.addUser(userPostModelApiToGetProfile);

        User user = userRepository.findById(userRegisteredModelApi.getId()).get();

        profile = Profile.builder()
                .user(user)
                .sex("Male")
                .interests(List.of())
                .job("Software Engineer")
                .school("University of Example")
                .description("I am a software engineer interested in technology.")
                .photo("profile-photo.jpg")
                .city("Example City")
                .build();

        profileRepository.save(profile);
    }

    @AfterEach
    void cleanData(){
        userRepository.deleteAll();
        profileRepository.deleteAll();
    }


    @Test
    @Transactional
    @WithMockUser
    void addUser_DataToAddUserGiven_ShouldAddNewUser() throws Exception {
        ResultActions postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiRoutes.Base.PATH + ApiRoutes.User.USERS)
                        .content(objectMapper.writeValueAsString(userPostModelApiToAdd))
                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.containsString("/users/")));

        String locationHeader = postResult.andReturn().getResponse().getHeader("Location");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ApiRoutes.Base.PATH + locationHeader))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo(userPostModelApiToAdd.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(userPostModelApiToAdd.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(userPostModelApiToAdd.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.equalTo(userPostModelApiToAdd.getPhoneNumber())));
        
    }


    @Test
    @Transactional
    void logInByCredentials_DataToAddUserGiven_ShouldAddNewUser() throws Exception{

        userLoginModelApi = new UserLoginModelApi()
                .username(userPostModelApiToGetProfile.getUsername())
                .password(userPostModelApiToGetProfile.getPassword());

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiRoutes.Base.PATH+ApiRoutes.User.AUTH+ApiRoutes.User.LOGIN)
                        .content(objectMapper.writeValueAsString(userLoginModelApi))
                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", Matchers.equalTo(userPostModelApiToGetProfile.getUsername())))
                .andExpect(jsonPath("$.jwtToken", Matchers.notNullValue()));
    }

    @Test
    @Transactional
    @WithMockUser()
    void getUserProfile_UserIdGiven_ShouldReturnUserProfile() throws Exception{

        Long userId = userRegisteredModelApi.getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ApiRoutes.Base.PATH + ApiRoutes.User.PATH + ApiRoutes.User.ID + ApiRoutes.User.PROFILE,userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sex", Matchers.equalTo(profile.getSex())))
                .andExpect(jsonPath("$.interests", Matchers.equalTo(profile.getInterests())))
                .andExpect(jsonPath("$.job", Matchers.equalTo(profile.getJob())))
                .andExpect(jsonPath("$.school", Matchers.equalTo(profile.getSchool())))
                .andExpect(jsonPath("$.description", Matchers.equalTo(profile.getDescription())))
                .andExpect(jsonPath("$.photo", Matchers.equalTo(profile.getPhoto())))
                .andExpect(jsonPath("$.city", Matchers.equalTo(profile.getCity())));
    }


}