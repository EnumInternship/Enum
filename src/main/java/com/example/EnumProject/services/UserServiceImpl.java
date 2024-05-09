package com.example.EnumProject.services;

import com.example.EnumProject.data.model.*;
import com.example.EnumProject.data.repository.UserRepository;
import com.example.EnumProject.dtos.request.*;
import com.example.EnumProject.dtos.response.*;
import com.example.EnumProject.security.services.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public SignUpResponse signUp(RegisterUserRequest registerUser) {
        User registeredUser = User.builder()
                .username(registerUser.getUsername())
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(registeredUser);


        String jwtToken = jwtService.generateToken( registeredUser);
        return SignUpResponse.builder()
                .email(registeredUser.getEmail())
                .token(jwtToken)
                .message("Successfully created user")
                .build();
    }



    public LoginResponse login(LoginUserRequest request) {

        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            User user = userRepository.findByUsername(request.getUsername()).orElse(null);
            if (user == null) {
                return null;
            }

            String jwtToken = jwtService.generateToken( user);

            return LoginResponse.builder()
                    .token(jwtToken)
                    .message("successfully logged in")
                    .build();
        } catch (AuthenticationException e) {
            return LoginResponse.builder().message( e.getMessage()).build();
        }
    }

    @Override
    public Post addPost(AddPostRequest addPostRequest) {
        return postService.addPost(addPostRequest);

    }

    @Override
    public ApiResponse<?> addComment(CommentRequest commentRequest) {
        return postService.addComment(commentRequest);
    }

    @Override
    public UpdateResponse updatePost(UpdatePostRequest postRequest) {
        return postService.updatePost(postRequest);
    }

    @Override
    public DeleteResponse deletePost(Long postId) {
        return postService.deletePost(postId);
    }

    @Override
    public ApiResponse<?> editComment(UpdateCommentReq comment) {
        return postService.editComment(comment);
    }
}
