package com.example.EnumProject.controller;


import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;
import com.example.EnumProject.services.PostService;
import com.example.EnumProject.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(@RequestBody AddPostRequest request) {
        return ResponseEntity.ok(userService.addPost(request));
    }

    @PostMapping("/addComment")
    public ResponseEntity <CommentResponse> addComment(@RequestBody CommentRequest request) {
        return ResponseEntity.ok(userService.addComment(request));
    }
    @PatchMapping("/updateComment")
    public ResponseEntity<UpdateResponse> updateComment(@RequestBody Post post){
        return ResponseEntity.ok(userService.updatePost(post, post.getId()));
    }

    @PatchMapping("/updatePost")
    public ResponseEntity<UpdateResponse> updatePost(@RequestBody Post post){
        return ResponseEntity.ok(userService.updatePost(post, post.getId()));
    }

    @DeleteMapping("deletePost")
    public ResponseEntity<DeleteResponse> deletePost(@RequestBody Post post){
        return ResponseEntity.ok(userService.deletePost(post.getId()));
    }




}
