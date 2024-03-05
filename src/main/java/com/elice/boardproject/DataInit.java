package com.elice.boardproject;

import com.elice.boardproject.board.repository.BoardRepository;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataInit {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
}
