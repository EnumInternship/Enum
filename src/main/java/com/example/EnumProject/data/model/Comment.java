package com.example.EnumProject.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private String author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    private Comment comment;
}
