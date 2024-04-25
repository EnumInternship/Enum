package com.example.EnumProject.services;

import com.example.EnumProject.data.model.*;
import com.example.EnumProject.data.repository.PostRepository;
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
