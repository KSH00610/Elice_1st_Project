package com.elice.boardproject.comment.service;

import com.elice.boardproject.comment.dto.CommentDto;
import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElse(null);
    }
    public List<Comment> getAllCommentsByPostId(Long post_id){
        List<Comment> list = new ArrayList<>();
        List<Comment> all = getAllComments();
        for(Comment c : all){
            if(c.getPost().getPost_id() == post_id){
                list.add(c);
            }
        }
        return list;
    }
    public Comment saveComment(String detail, Post post){
        Comment comment = new Comment(detail, post);
        return commentRepository.save(comment);
    }
    public Comment saveComment(CommentDto commentDto, Post post){
        return commentRepository.save(commentDto.toEntity(post));
    }

    public Comment updateComment(Long comment_id,String detail){
        //Comment comment = commentRepository.findById(post_id).orElse(null);
        Comment comment = getCommentById(comment_id);
        comment.setDetail(detail);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long comment_id,CommentDto commentDto){
        //Comment comment = commentRepository.findById(post_id).orElse(null);
        Comment comment = getCommentById(comment_id);
        comment.setDetail(commentDto.getDetail());
        return commentRepository.save(comment);
    }
    public void deleteComment(Long id){
        Comment comment =commentRepository.findById(id).orElse(null);
        commentRepository.delete(comment);
    }
}
