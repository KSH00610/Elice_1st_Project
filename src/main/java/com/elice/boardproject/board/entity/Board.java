package com.elice.boardproject.board.entity;

import com.elice.boardproject.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    Long board_id;

    @Column(name="board_name", nullable = false)
    String board_name;

    @Column(name="board_description", nullable = false)
    String board_description;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Post> posts = new ArrayList<>();
    public Board(String board_name, String board_description){
        this.board_name=board_name;
        this.board_description=board_description;
    }
}
