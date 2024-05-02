package com.example.EnumProject.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;
    private String title;
    private String content;
    private String author;
    private String dateCreated;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Comment>comments;
}
