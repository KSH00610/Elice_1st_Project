package com.elice.boardproject.post.repository;

import com.elice.boardproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Optional<Post> findById(Long id);

  //  List<Post> findByBoard_BoardId(Long boardId);

    Post save(Post post);

    void delete(Post post);
}
