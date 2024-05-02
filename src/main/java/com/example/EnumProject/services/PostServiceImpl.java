package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.model.User;
import com.example.EnumProject.data.repository.CommentRepository;
import com.example.EnumProject.data.repository.PostRepository;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.request.UpdateCommentReq;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;
import com.example.EnumProject.exception.PostException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final CommentService commentService;


    @Override
    public Post addPost(AddPostRequest addPostRequest) {
        log.info("Adding new post: {}", addPostRequest);
        var post = Post.builder()
                .title(addPostRequest.getTitle())
                .content(addPostRequest.getContent())
                .author(addPostRequest.getAuthor())
                .user(User.builder().build())
                .dateCreated(String.valueOf(addPostRequest.getCreatedAt()))
                .build();
        postRepository.save(post);
        log.info("Post added: {}", post);
//
//        if (post.getComments().isEmpty()) {
//            log.info("Comment is empty");
//            post.setComments(List.of(Comment.builder()
//                    .content("Be the first one to comment!")
//                    .build()));
//        }
        log.info("i got here!!!");
        return post;
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public ApiResponse<?> addComment(CommentRequest commentRequest) {
        Optional<Post> foundPost = postRepository.findPostById(commentRequest.getId());
        if (foundPost.isPresent()) {
            Comment response = commentService.addCommentToPost(commentRequest);

            return ApiResponse.success(response, response.getContent());
        }
        else return ApiResponse.error("Post Not Found");


    }

    @Override
    public UpdateResponse updatePost(Post post, Long postId) {
        Post updatedPost = postRepository.findById(postId).orElseThrow(()->
                new PostException("Post not found"));

        updatedPost.setTitle(post.getTitle());
        updatedPost.setContent(post.getContent());
        updatedPost.setAuthor(post.getAuthor());
        postRepository.save(updatedPost);


        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setMessage("Successfully updated post");
        return updateResponse;
    }

    @Override
    public DeleteResponse deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new PostException("Post not found"));

        postRepository.delete(post);

        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Post successfully deleted");
        return deleteResponse;
    }

    @Override
    public ApiResponse<?> editComment(UpdateCommentReq comment) {
        ApiResponse<?> updatedComment = commentService.editComment(comment);
        return ApiResponse.success(updatedComment, updatedComment.getMessage());
    }
}
