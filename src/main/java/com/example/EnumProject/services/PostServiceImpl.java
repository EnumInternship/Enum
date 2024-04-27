package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.repository.CommentRepository;
import com.example.EnumProject.data.repository.PostRepository;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
import com.example.EnumProject.dtos.response.CommentResponse;
import com.example.EnumProject.dtos.response.DeleteResponse;
import com.example.EnumProject.dtos.response.UpdateResponse;
import com.example.EnumProject.exception.PostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;


    @Override
    public Post addPost(AddPostRequest addPostRequest) {
        var post = Post.builder()
                .title(addPostRequest.getTitle())
                .content(addPostRequest.getContent())
                .author(addPostRequest.getAuthor())
                .build();
        postRepository.save(post);

        return post;
    }

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
      Comment response = commentService.addCommentToPost(commentRequest);

      CommentResponse commentResponse = new CommentResponse();
      commentResponse.setMessage(String.valueOf(response));
      return commentResponse;
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
    public UpdateResponse editComment(Comment comment, Long id) {

        return commentService.editComment(comment, id );
    }
}
