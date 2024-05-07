package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.repository.CommentRepository;
import com.example.EnumProject.data.repository.PostRepository;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.request.UpdateCommentReq;
import com.example.EnumProject.dtos.response.ApiResponse;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;
import com.example.EnumProject.exception.CommentException;
import com.example.EnumProject.exception.PostException;
import com.example.EnumProject.exception.PostNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public Comment addCommentToPost(CommentRequest commentRequest) {

        Optional<Post> optionalPost = postRepository.findById(commentRequest.getId());
        if (optionalPost.isEmpty()) {
            throw new PostNotFoundException("Post not found with ID: " + commentRequest.getPostId());
        }
        String content = commentRequest.getComment();
        if(content == null){
            throw new CommentException("Comment can not be empty");
        }

        Post post = optionalPost.get();
      Comment comment = Comment.builder()
              .author(commentRequest.getAuthor())
              .content(content)
              .post(post)
              .build();
         commentRepository.save(comment);
         return comment;
    }


    @Override
    public ApiResponse<?> updateComment(CommentRequest commentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(commentRequest.getId());
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setAuthor(commentRequest.getAuthor());
            existingComment.setContent(commentRequest.getContent());
            Comment updatedComment = commentRepository.save(existingComment);
            return ApiResponse.success(updatedComment, "Successfully updated comment");
        } else {
            return ApiResponse.error("Comment not found");
        }
    }


    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public ApiResponse<?> editComment(UpdateCommentReq comment) {
        Comment updatedComment = commentRepository.findCommentById(comment.getId()).orElseThrow(()->
                new PostException("Comment not found"));

        updatedComment.setContent(comment.getContent());
        updatedComment.setId(comment.getId());
        commentRepository.save(updatedComment);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setMessage("Successfully updated comment");
        return ApiResponse.success(updateResponse, "Successfully updated comment");
    }

    @Override
    public DeleteResponse deleteComment(Long id) {

        Comment comment = commentRepository.findCommentById(id).orElseThrow(()->
                new PostException("Comment not found"));
        commentRepository.delete(comment);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Comment deleted successfully");

        return deleteResponse;
    }

    @Override
    public ApiResponse<?> addCommentToComment(Comment comment, Long id) {
        Comment addComment = commentRepository.findCommentById(id).orElse(null);
        assert addComment != null;
        addComment.setComment(comment.getComment());
        addComment.setAuthor(comment.getAuthor());
       Comment savedComment = commentRepository.save(addComment);

       return ApiResponse.success(savedComment, "Successfully added comment");
    }
}
