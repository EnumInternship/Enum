package com.example.EnumProject.data.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate dateCreated;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Comment>comments;
}
