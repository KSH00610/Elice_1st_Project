package com.elice.boardproject.post.controller;

import com.elice.boardproject.board.dto.BoardDto;
import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.post.dto.PostDto;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final BoardService boardService;
    @Autowired
    public PostController(PostService postService, BoardService boardService){
        this.postService = postService;
        this.boardService = boardService;
    }
    @GetMapping("/{board_id}")
    public String getAllPosts(@PathVariable("board_id") Long board_id,Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        List<Post> posts = postService.getAllPostsByBoardId(board_id);
        model.addAttribute("postList", posts);
        model.addAttribute("board_id", board_id);
        return "post/new_posts";
    }
    @GetMapping("/{board_id}/{post_id}")
    public String getPostById(@PathVariable("board_id") Long board_id,@PathVariable("post_id") Long post_id, Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        if(postService.getPostById(post_id) == null){
            return "존재하지 않는 Posts";
        }
        Post post = postService.getPostById(post_id);
        if(post == null){
            return "error";
        }
        model.addAttribute("post",post);
        return "post/new_post";
    }
    @GetMapping("/create/{board_id}")
    public String getCreatePost(@PathVariable("board_id") Long board_id,Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        model.addAttribute("board_id", board_id);
        return "post/new_createpost";
    }
    @GetMapping("/edit/{board_id}/{post_id}")
    public String getEditPost(@PathVariable("board_id") Long board_id,@PathVariable("post_id") Long post_id, Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        if(postService.getPostById(post_id) == null){
            return "존재하지 않는 Posts";
        }
        Post post = postService.getPostById(post_id);
        if(post == null){
            return "error";
        }
        model.addAttribute("post",post);
        return "post/new_editpost";
    }
    @PostMapping("/create/{board_id}")
    public String createPost(@ModelAttribute("postDto") PostDto postDto, @PathVariable("board_id") Long board_id) {
        postService.savePost(postDto,boardService.getBoardById(board_id));
        return "redirect:/posts/" + board_id;
    }

    @PostMapping("/edit/{board_id}/{post_id}")
    public String putPost(@PathVariable("board_id") Long board_id,@PathVariable("post_id") Long post_id, @ModelAttribute("postDto") PostDto postDto) {
        postService.updatePost(post_id, postDto);
        return "redirect:/posts/" + board_id;
    }
//    @PutMapping("/{board_id}/{post_id}")
//    public ResponseEntity<Post> putPost(@PathVariable("board_id") Long board_id,@PathVariable("post_id") Long post_id, @RequestBody Map<String, String> map){
//        String title = map.get("title");
//        String description = map.get("description");
//
//        Post updatedPost = postService.updatePost(post_id,title,description);
//        if (updatedPost != null) {
//            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @DeleteMapping("delete/{board_id}/{post_id}")
    public String deletePost(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id){
        postService.deletePost(post_id);
        return "redirect:/posts/" + board_id;
    }
}
