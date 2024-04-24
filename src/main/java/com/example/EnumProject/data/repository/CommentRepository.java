package com.example.EnumProject.data.repository;

import com.example.EnumProject.data.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
