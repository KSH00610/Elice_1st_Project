package com.elice.boardproject.comment.dto;

import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.post.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    String detail;

    public CommentDto(String detail){
        this.detail=detail;
    }

    public String getDetail(){
        return detail;
    }
    public void setDetail(String detail){
        this.detail=detail;
    }

    public Comment toEntity(Post post){
        return new Comment(detail,post);
    }
}
