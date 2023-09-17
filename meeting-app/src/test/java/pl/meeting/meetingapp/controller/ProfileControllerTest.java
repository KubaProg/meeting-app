package pl.meeting.meetingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.meeting.meetingapp.ApiRoutes;
import pl.meeting.meetingapp.MeetingAppApplication;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.models.UserLoginModelApi;
import pl.meeting.meetingapp.models.UserModelApi;
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;
import pl.meeting.meetingapp.repository.ProfileRepository;
import pl.meeting.meetingapp.repository.UserRepository;
import pl.meeting.meetingapp.service.UserService;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingAppApplication.class)
@AutoConfigureMockMvc
class ProfileControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;
    ObjectMapper mapper = new ObjectMapper();
    Profile profile;
    UserPostModelApi userPostModelApi;
    UserRegisteredModelApi userRegisteredModelApi;


    @BeforeEach
    void initializeData(){
         userPostModelApi = new UserPostModelApi()
                .username("user1@usermail.com")
                .password("hardpassword")
                .firstName("name")
                .surname("surname")
                .phoneNumber("123456789");
         userRegisteredModelApi = userService.addUser(userPostModelApi);

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
    @WithMockUser()
    void getUserProfile_UserIdGiven_ShouldReturnUserProfile() throws Exception{

        Long userId = userRegisteredModelApi.getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ApiRoutes.Base.PATH + ApiRoutes.Profile.PATH + ApiRoutes.Profile.USER_ID,userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(profile.getId().intValue())))
                .andExpect(jsonPath("$.sex", Matchers.equalTo(profile.getSex())))
                .andExpect(jsonPath("$.interests", Matchers.equalTo(profile.getInterests())))
                .andExpect(jsonPath("$.job", Matchers.equalTo(profile.getJob())))
                .andExpect(jsonPath("$.school", Matchers.equalTo(profile.getSchool())))
                .andExpect(jsonPath("$.description", Matchers.equalTo(profile.getDescription())))
                .andExpect(jsonPath("$.photo", Matchers.equalTo(profile.getPhoto())))
                .andExpect(jsonPath("$.city", Matchers.equalTo(profile.getCity())));
    }


//    @Test
//    void deleteProfileByUserId() {
//    }

}