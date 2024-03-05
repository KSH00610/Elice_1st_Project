package com.elice.boardproject.comment.entity;

import com.elice.boardproject.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long comment_id;

    @Column(name="detail", nullable = false)
    String detail;

    @Column(name="create_time", nullable = false)
    String createdTime;

    @ManyToOne
    @JoinColumn(name="post_id")
    Post post;
    public Comment(String detail, Post post){
        this.detail=detail;
        this.createdTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.post=post;
    }
}
