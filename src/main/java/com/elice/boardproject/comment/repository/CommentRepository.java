package com.elice.boardproject.comment.repository;

import com.elice.boardproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);

    void delete(Comment comment);
}
