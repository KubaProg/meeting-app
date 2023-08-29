package pl.meeting.meetingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meeting.meetingapp.config.JwtService;
import pl.meeting.meetingapp.dto.UserGetDto;
import pl.meeting.meetingapp.dto.UserLoginDto;
import pl.meeting.meetingapp.dto.UserPostDto;
import pl.meeting.meetingapp.dto.UserPutDto;
import pl.meeting.meetingapp.entity.Role;
import pl.meeting.meetingapp.entity.User;
import pl.meeting.meetingapp.mapper.UserMapper;
import pl.meeting.meetingapp.models.UserRegisteredModelApi;
import pl.meeting.meetingapp.repository.RoleRepository;
import pl.meeting.meetingapp.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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



    @Transactional
    public UserGetDto logInByCredentials(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = authenticateUser(username,password);

        UserGetDto userGetDto = userMapper.mapToUserGetDto(user);
        userGetDto.setJwtToken(jwtToken);

        return userGetDto;
    }

    public List<UserGetDto> getAllUsers()
    {
        List<UserGetDto> users = userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserGetDto)
                .collect(Collectors.toList());

        return users;
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
        userGetDto.setRoleIds(userPostDto.getRoleIds());

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


}
