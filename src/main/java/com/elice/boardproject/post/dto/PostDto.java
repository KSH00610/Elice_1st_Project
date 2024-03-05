package com.elice.boardproject.post.dto;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
public class PostDto {
    String title;
    String description;
    public PostDto(String title, String description){
        this.title=title;
        this.description=description;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public Post toEntity(Board board){
        return new Post(getTitle(), getDescription(), board);
    }
}
