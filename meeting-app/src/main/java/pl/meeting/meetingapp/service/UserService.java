package pl.meeting.meetingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meeting.meetingapp.config.JwtService;
import pl.meeting.meetingapp.entity.Profile;
import pl.meeting.meetingapp.entity.Role;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.mapper.ProfileMapper;
import pl.meeting.meetingapp.mapper.UserMapper;
import pl.meeting.meetingapp.models.*;
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

    public List<UserModelApi> getAllUsers()
    {
        return userRepository.findAll()
                .stream().map(userMapper::mapToUserModelApi)
                .collect(Collectors.toList());
    }



    @Transactional
    public UserRegisteredModelApi addUser(UserPostModelApi userPostModelApi)
    {

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userPostModelApi.getRoleIds()));

        User userToSave = User.builder()
                .firstName(userPostModelApi.getFirstName())
                .surname(userPostModelApi.getSurname())
                .username(userPostModelApi.getUsername())
                .password(passwordEncoder.encode(userPostModelApi.getPassword()))
                .phoneNumber(userPostModelApi.getPhoneNumber())
                .roles(roles)
                .build();

        User savedUser = userRepository.save(userToSave);
        String jwtToken = authenticateUser(userPostModelApi.getUsername(),userPostModelApi.getPassword());


        UserRegisteredModelApi userRegisteredModelApi = userMapper.mapToUserRegisteredModelApi(savedUser);
        userRegisteredModelApi.setJwtToken(jwtToken);
        return userRegisteredModelApi;
    }

    @Transactional
    public UserModelApi putUserById(Long id, UserPutModelApi userPutModelApi)
    {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with this id not found"));

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userPutModelApi.getRoleIds()));
        if(roles.isEmpty()){
            throw new RuntimeException("No roles with such ids found");
        }

        user.setId(id);
        user.setFirstName(userPutModelApi.getFirstName());
        user.setSurname(userPutModelApi.getSurname());
        user.setUsername(userPutModelApi.getUsername());
        user.setPassword(passwordEncoder.encode(userPutModelApi.getPassword()));
        user.setPhoneNumber(userPutModelApi.getPhoneNumber());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return userMapper.mapToUserModelApi(savedUser);
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

    @Transactional
    public void deleteUserProfile(Long userId) {
        profileRepository.deleteProfileByUserId(userId);
    }

}
