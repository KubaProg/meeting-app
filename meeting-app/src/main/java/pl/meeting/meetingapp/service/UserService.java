package pl.meeting.meetingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meeting.meetingapp.config.JwtService;
import pl.meeting.meetingapp.dto.UserDto.UserGetDto;
import pl.meeting.meetingapp.dto.UserDto.UserLoginDto;
import pl.meeting.meetingapp.dto.UserDto.UserPostDto;
import pl.meeting.meetingapp.dto.UserDto.UserPutDto;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.entity.Role;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.mapper.ProfileMapper;
import pl.meeting.meetingapp.mapper.UserMapper;
import pl.meeting.meetingapp.models.ProfileModelApi;
import pl.meeting.meetingapp.models.UserLoginModelApi;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;
import pl.meeting.meetingapp.repository.ProfileRepository;
import pl.meeting.meetingapp.repository.RoleRepository;
import pl.meeting.meetingapp.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;



    @Transactional
    public UserRegisteredModelApi logInByCredentials(UserLoginModelApi userLoginModelApi) {
        String username = userLoginModelApi.getUsername();
        String password = userLoginModelApi.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = authenticateUser(username,password);

        UserRegisteredModelApi userRegisteredModelApi = userMapper.mapToUserRegisteredModelApi(user);
        userRegisteredModelApi.setJwtToken(jwtToken);

        return userRegisteredModelApi;
    }

    public List<UserGetDto> getAllUsers()
    {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserGetDto)
                .collect(Collectors.toList());
    }



    @Transactional
    public UserGetDto addUser(UserPostDto userPostDto)
    {

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userPostDto.getRoleIds()));

        User userToSave = User.builder()
                .firstName(userPostDto.getFirstName())
                .surname(userPostDto.getSurname())
                .username(userPostDto.getUsername())
                .password(passwordEncoder.encode(userPostDto.getPassword()))
                .phoneNumber(userPostDto.getPhoneNumber())
                .roles(roles)
                .build();

        User savedUser = userRepository.save(userToSave);
        String jwtToken = authenticateUser(userPostDto.getUsername(),userPostDto.getPassword());

        UserGetDto userGetDto = userMapper.mapToUserGetDto(savedUser);
        userGetDto.setJwtToken(jwtToken);

        return userGetDto;
    }

    @Transactional
    public UserGetDto putUserById(Long id, UserPutDto userPutDto)
    {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with this id not found"));

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userPutDto.getRoleIds()));
        if(roles.isEmpty()){
            throw new RuntimeException("No roles with such ids found");
        }

        user.setId(id);
        user.setFirstName(userPutDto.getFirstName());
        user.setSurname(userPutDto.getSurname());
        user.setUsername(userPutDto.getUsername());
        user.setPassword(passwordEncoder.encode(userPutDto.getPassword()));
        user.setPhoneNumber(userPutDto.getPhoneNumber());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return userMapper.mapToUserGetDto(savedUser);
    }

    public String authenticateUser(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        var user = userRepository.findByUsername(username)
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }

    @Transactional
    public void deleteUserById(Long id){
        if(id == null){
            throw new RuntimeException();
        }

        User user = getUser(id);

        userRepository.delete(user);
    }


    private User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public ProfileModelApi getUserProfile(Long userId){
        Profile profile = profileRepository.findProfileByUserId(userId).orElseThrow(NoSuchElementException::new);

        return profileMapper.mapToProfileModelApi(profile);

    }

}
