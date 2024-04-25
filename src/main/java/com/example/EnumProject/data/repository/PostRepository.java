package com.example.EnumProject.data.repository;

import com.example.EnumProject.data.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);

    Optional<Post> findPostById(Long id);
}
