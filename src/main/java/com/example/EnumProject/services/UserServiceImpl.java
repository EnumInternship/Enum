package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Cohort;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.Role;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.data.repository.PostRepository;
import com.example.EnumProject.data.repository.UserRepository;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.LoginCohortRequest;
import com.example.EnumProject.dtos.request.LoginUserRequest;
import com.example.EnumProject.dtos.request.RegisterUserRequest;
import com.example.EnumProject.dtos.response.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final PostService postService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public AuthResponse signUp(RegisterUserRequest registerUser) {
        var registeredUser = User.builder()
                .username(registerUser.getUsername())
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(registeredUser);


        var jwtToken = jwtService.generateToken((UserDetails) registeredUser);
        return AuthResponse.builder()
                .token(jwtToken)
                .message("Successfully created user")
                .build();
    }



    public AuthResponse login(LoginUserRequest request) {

        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            log.info("User {} authenticated successfully", request.getEmail());

            var user = userRepository.findByEmail(request.getEmail()).orElse(null);
            if (user == null) {
                log.error("User {} not found in the database", request.getEmail());
                return null;
            }
            log.info("Found user {}", user);

            var jwtToken = jwtService.generateToken((UserDetails) user);
            log.info("Successfully authenticated user {}", request.getEmail());

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user {}", request.getEmail(), e);
            return null;
        }
    }

    @Override
    public Post addPost(AddPostRequest addPostRequest) {
        return postService.addPost(addPostRequest);
    }
}
