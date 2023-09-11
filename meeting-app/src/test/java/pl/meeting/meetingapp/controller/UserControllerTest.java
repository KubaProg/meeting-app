package pl.meeting.meetingapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.meeting.meetingapp.config.JwtService;
import pl.meeting.meetingapp.dto.UserDto.UserGetDto;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.entity.Role;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.mapper.ProfileMapper;
import pl.meeting.meetingapp.mapper.ProfileMapperImpl;
import pl.meeting.meetingapp.mapper.UserMapper;
import pl.meeting.meetingapp.mapper.UserMapperImpl;
import pl.meeting.meetingapp.models.UserModelApi;
import pl.meeting.meetingapp.models.UserPostModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;
import pl.meeting.meetingapp.repository.ProfileRepository;
import pl.meeting.meetingapp.repository.RoleRepository;
import pl.meeting.meetingapp.repository.UserRepository;
import pl.meeting.meetingapp.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ProfileRepository profileRepository;


//    private final ProfileMapper profileMapper = new ProfileMapperImpl();
//
//
//    private final UserMapper userMapper = new UserMapperImpl();

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

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

    @BeforeEach
    public void setUp() {
        // initializing mocks
        MockitoAnnotations.initMocks(this);

        // setting up userRepository
        User user = new User();
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));


        // setting up authenticationManager
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);

        // setting up jwtService
        when(jwtService.generateToken(user)).thenReturn("mockedToken");


//        // setting up userService
//        UserRegisteredModelApi userRegisteredModelApi = userMapper.mapToUserRegisteredModelApi(user);
//        userRegisteredModelApi.setJwtToken("mockedToken");
//
//        when(userService.logInByCredentials(userMapper.mapToUserLoginModelApi(user))).thenReturn(
//                userRegisteredModelApi
//        );
    }

//    @Test
//    void logInByCredentials_returnsToken() {
//        User testuser = userRepository.findByUsername("testuser").get();
//        String token = userController.logInByCredentials(userMapper.mapToUserLoginModelApi(testuser)).getBody().getJwtToken();
//        assertEquals("mockedToken", token);
//    }



//    @Test
//    void getAllUsers() {
//
//        UserModelApi user = new UserModelApi();
//
//        List<UserModelApi> users = List.of(user);
//        when(userService.getAllUsers()).thenReturn(users);
//
//        List<UserModelApi> allUsers = userController.getAllUsers().getBody();
//
//        assertEquals(1, allUsers.size());
//
//    }

    @Test
    void getUserProfile() {
    }

    @Test
    void deleteUserById() {
//        UserController userController = mock(UserController.class);


//        userController.deleteProfileByUserId(1L);

//        ProfileModelApi userProfile = userController.getUserProfile(1L).getBody();


//        assertEquals(2,userProfile);

    }
}