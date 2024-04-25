package com.example.EnumProject.services;

import com.example.EnumProject.data.model.Comment;
import com.example.EnumProject.data.model.Post;
import com.example.EnumProject.data.repository.CommentRepository;
import com.example.EnumProject.data.repository.PostRepository;
import com.example.EnumProject.dtos.request.AddPostRequest;
import com.example.EnumProject.dtos.request.CommentRequest;
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
    public Comment addComment(CommentRequest commentRequest) {
      return commentService.addCommentToPost(commentRequest);
    }
}
