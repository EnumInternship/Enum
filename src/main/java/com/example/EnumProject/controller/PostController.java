package com.example.EnumProject.controller;


import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.request.UpdateCommentReq;
import com.example.EnumProject.dtos.response.ApiResponse;
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
    private final UserService userService;

    @PostMapping("/createPost")
    public ApiResponse<Post> createPost(@RequestBody AddPostRequest request) {
        log.info("Creating new post: {}", request);
        try {
            log.info("Creating new post: {}", request);
            return ApiResponse.success(userService.addPost(request), "Post Added Successfully");

        }catch (Exception e){
            log.error(e.getMessage());
            return ApiResponse.error("Error while adding post");
        }
    }

    @PostMapping("/addComment")
    public ApiResponse<?> addComment(@RequestBody CommentRequest request) {

        return ApiResponse.success(userService.addComment(request ),
                "Comment added");
    }
    @PatchMapping("/editComment")
    public ApiResponse<?> editComment(@RequestBody UpdateCommentReq comment){
        return ApiResponse.success(userService.editComment(comment),
                "Comment updated");
    }



    @PatchMapping("/updatePost")
    public ApiResponse<?> updatePost(@RequestBody Post post){
        return ApiResponse.success(userService.updatePost(post, post.getId()), "Post Updated");
    }

    @DeleteMapping("deletePost")
    public ApiResponse<?> deletePost(@RequestBody Post post){
        return ApiResponse.success(userService.deletePost(post.getId()),  "Post Deleted");
    }




}
