package com.elice.boardproject.post.entity;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    Long post_id;

    @Column(name="title", nullable = false)
    String title;

    @Column(name="description", nullable = false)
    String description;

    @ManyToOne
    @JoinColumn(name="board_id")
    Board board;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonIgnore
    List<Comment> comments = new ArrayList<>();
    public Post(String title, String description, Board board){
        this.title=title;
        this.description=description;
        this.board=board;
    }
}
