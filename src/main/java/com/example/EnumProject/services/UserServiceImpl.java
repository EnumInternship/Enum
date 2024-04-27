package com.example.EnumProject.services;

import com.example.EnumProject.data.model.*;
import com.example.EnumProject.data.repository.UserRepository;
import com.example.EnumProject.dtos.request.*;
import com.example.EnumProject.dtos.response.AuthResponse;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signUp(RegisterUserRequest registerUser) {
        var registeredUser = User.builder()
                .username(registerUser.getUsername())
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(registeredUser);


        var jwtToken = jwtService.generateToken( registeredUser);
        return AuthResponse.builder()
                .token(jwtToken)
                .message("Successfully created user")
                .build();
    }



    public AuthResponse login(LoginUserRequest request) {
//        System.out.println("i got here");
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        System.out.println("i am at the provision manager");
//        var user = userRepository.findByEmail(request.getEmail()).orElse(null);
//        System.out.println("found guy");
//        var token =jwtService.generateToken(user);
//        return AuthResponse.builder().token(token).build();

        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            log.info("User {} authenticated successfully", request.getUsername());

            var user = userRepository.findByUsername(request.getUsername()).orElse(null);
            if (user == null) {
                log.error("User {} not found in the database", request.getUsername());
                return null;
            }
            log.info("Found user {}", user);

            var jwtToken = jwtService.generateToken( user);
            log.info("Successfully authenticated user {}", request.getUsername());

            return AuthResponse.builder()
                    .token(jwtToken)
                    .message("successfully logged in")
                    .build();
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user {}", request.getUsername(), e);
            return AuthResponse.builder().message("Authentication failed for user {}" + e.getMessage()).build();
        }
    }

    @Override
    public Post addPost(AddPostRequest addPostRequest) {
        return postService.addPost(addPostRequest);
    }

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
        return postService.addComment(commentRequest);
    }

    @Override
    public UpdateResponse updatePost(Post post, Long postId) {
        return postService.updatePost(post, postId);
    }

    @Override
    public DeleteResponse deletePost(Long postId) {
        return postService.deletePost(postId);
    }
}
