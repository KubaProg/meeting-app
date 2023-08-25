package pl.management.workshopmanagement.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.management.workshopmanagement.config.JwtService;
import pl.management.workshopmanagement.entity.Role;
import pl.management.workshopmanagement.entity.User;
import pl.management.workshopmanagement.repository.RoleRepository;
import pl.management.workshopmanagement.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        Role roleToAdd = roleRepository.findRoleByRoleName("ROLE_USER").get();

        var user = User.builder()
                .firstName(request.getFirstName())
                .surname(request.getLastName())
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(roleToAdd))
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
