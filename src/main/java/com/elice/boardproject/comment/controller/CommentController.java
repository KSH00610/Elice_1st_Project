package com.elice.boardproject.comment.controller;

import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.comment.dto.CommentDto;
import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.service.CommentService;
import com.elice.boardproject.post.dto.PostDto;
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
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final BoardService boardService;
    @Autowired
    public CommentController(CommentService commentService, PostService postService, BoardService boardService){
        this.commentService=commentService;
        this.postService=postService;
        this.boardService=boardService;
    }
    @GetMapping("/{board_id}/{post_id}")
    public String getAllComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id, Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        if(postService.getPostById(post_id) == null){
            return "존재하지 않는 Posts";
        }
        List<Comment> list=commentService.getAllCommentsByPostId(post_id);
        model.addAttribute("commentList", list);
        model.addAttribute("board_id", board_id);
        model.addAttribute("post_id", post_id);
        return "comment/new_comments";
    }
    @GetMapping("/{board_id}/{post_id}/{comment_id}")
    public String getComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id,@PathVariable("comment_id") Long comment_id, Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        if(postService.getPostById(post_id) == null){
            return "존재하지 않는 Posts";
        }
        if(commentService.getCommentById(comment_id)==null){
            return "존재하지 않는 Comments";
        }
        Comment comment =commentService.getCommentById(comment_id);
        model.addAttribute("comment", comment);
        return "comment/new_comment";
    }
    @GetMapping("/create/{board_id}/{post_id}")
    public String getCreateComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id, Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        if(postService.getPostById(post_id) == null){
            return "존재하지 않는 Posts";
        }
        model.addAttribute("board_id", board_id);
        model.addAttribute("post_id", post_id);
        return "comment/new_createcomment";
    }
    @GetMapping("/edit/{board_id}/{post_id}/{comment_id}")
    public String getEditComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id,@PathVariable("comment_id") Long comment_id, Model model){
        if(boardService.getBoardById(board_id) == null){
            return "존재하지 않는 Boards";
        }
        if(postService.getPostById(post_id) == null){
            return "존재하지 않는 Posts";
        }
        if(commentService.getCommentById(comment_id)==null){
            return "존재하지 않는 Comments";
        }
        Comment comment =commentService.getCommentById(comment_id);
        model.addAttribute("comment", comment);
        return "comment/new_editcomment";
    }
    @PostMapping("/edit/{board_id}/{post_id}/{comment_id}")
    public String putComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id,@PathVariable("comment_id") Long comment_id, @ModelAttribute("commentDto") CommentDto commentDto) {
        commentService.updateComment(comment_id, commentDto);
        return "redirect:/comments/" + board_id + "/" +post_id;
    }
    @PostMapping("/create/{board_id}/{post_id}")
    public String createComment(@ModelAttribute("commentDto") CommentDto commentDto, @PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id){
        commentService.saveComment(commentDto, postService.getPostById(post_id));
        return "redirect:/comments/" + board_id + "/" + post_id;
    }

//    @PutMapping("/{board_id}/{post_id}/{comment_id}")
//    public ResponseEntity<Comment> putComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id,@PathVariable("comment_id") Long comment_id, @RequestBody Map<String, String> map){
//        String detail = map.get("detail");
//        Comment updateComment = commentService.updateComment(comment_id,detail);
//        if (updateComment != null) {
//            return new ResponseEntity<>(updateComment, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("delete/{board_id}/{post_id}/{comment_id}")
    public String deleteComment(@PathVariable("board_id") Long board_id, @PathVariable("post_id") Long post_id,@PathVariable("comment_id") Long comment_id){
        commentService.deleteComment(comment_id);
        return "redirect:/comments/" + board_id + "/" +post_id;
    }
}