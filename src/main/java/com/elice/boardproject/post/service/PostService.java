package com.elice.boardproject.post.service;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.post.dto.PostDto;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public Post savePost(String title, String descrption, Board board){
        Post post = new Post(title, descrption,board);
        return postRepository.save(post);
    }
    public Post savePost(PostDto postDTO, Board board) {
        return postRepository.save(postDTO.toEntity(board));
    }
    public List<Post> getAllPostsByBoardId(Long boardId) {
        List<Post> list = new ArrayList<>();
        List<Post> all = getAllPosts();
        for(Post p : all){
            if(p.getBoard().getBoard_id() == boardId){
                list.add(p);
            }
        }
        return list;
    }

    public Post updatePost(Long boardId, String title,String description){
        //Post post = postRepository.findById(boardId).orElse(null);
        Post post = getPostById(boardId);
        post.setTitle(title);
        post.setDescription(description);
        return postRepository.save(post);
    }
    public Post updatePost(Long postId, PostDto postDto){
        //Post post = postRepository.findById(boardId).orElse(null);
        Post post = getPostById(postId);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        return postRepository.save(post);
    }
    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElse(null);
        postRepository.delete(post);
    }
}

