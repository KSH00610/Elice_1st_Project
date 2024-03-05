package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.dto.BoardDto;
import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping
    public String getAllBoard(Model model){
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boardList", boards);
        return "board/new_boards";
    }
    @GetMapping("/{board_id}")
    public String getBoardDetail(@PathVariable("board_id") Long board_id, Model model) {
        Board board = boardService.getBoardById(board_id);
        if (board == null) {
            // 게시판이 없을 경우에 대한 처리
            return "error"; // 에러 페이지로 이동하거나 다른 처리를 수행할 수 있습니다.
        }
        model.addAttribute("board", board);
        return "board/new_board";
    }
    @GetMapping("/create")
    public String getCreateBoard(Model model){
        return "board/new_createboard";
    }
    @GetMapping("/edit/{board_id}")
    public String getEditBoard(@PathVariable("board_id") Long board_id, Model model) {
        Board board = boardService.getBoardById(board_id);
        if (board == null) {
            // 게시판이 없을 경우에 대한 처리
            return "error"; // 에러 페이지로 이동하거나 다른 처리를 수행할 수 있습니다.
        }
        model.addAttribute("board", board);
        return "board/new_editboard";
    }
    @PostMapping("/create")
    public String createBoard(@ModelAttribute("boardDto") BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return "redirect:/boards"; // 리다이렉트하여 게시판 목록 페이지로 이동합니다.
    }
//    @PutMapping("/edit/{board_id}")
//    public String putBoard(@PathVariable("board_id") Long board_id, @RequestBody Map<String, String> map) {
//        String board_name = map.get("board_name");
//        String board_description = map.get("board_description");
//        boardService.updateBoard(board_id, board_name,board_description);
//        return "redirect:/boards";
//    }
//    @PutMapping("/edit/{board_id}")
//    public String putBoard(@PathVariable("board_id") Long board_id, @ModelAttribute("boardDto") BoardDto boardDto) {
//        boardService.updateBoard(board_id, boardDto);
//        return "redirect:/boards";
//    }
    @PostMapping("/edit/{board_id}")
    public String putBoard(@PathVariable("board_id") Long board_id, @ModelAttribute("boardDto") BoardDto boardDto) {
        boardService.updateBoard(board_id, boardDto);
        return "redirect:/boards";
    }
    @DeleteMapping("/delete/{board_id}")
    public String deleteBoard(@PathVariable("board_id") Long board_id){
        boardService.deleteBoard(board_id);
        return "redirect:/boards";
    }
}
