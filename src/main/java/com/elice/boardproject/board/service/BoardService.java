package com.elice.boardproject.board.service;

import com.elice.boardproject.board.dto.BoardDto;
import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    @Autowired
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id){
        return boardRepository.findById(id).orElse(null);
    }

    public Board saveBoard(String board_name, String board_descrption){
        Board board = new Board();
        board.setBoard_name(board_name);
        board.setBoard_description(board_descrption);
        return boardRepository.save(board);
    }

    public Board saveBoard(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity());
    }
    public Board updateBoard(Long boardId, BoardDto boardDto) {
        Board board = boardRepository.findById(boardId).orElse(null);
        board.setBoard_name(boardDto.getBoard_name());
        board.setBoard_description(boardDto.getBoard_description());
        return boardRepository.save(board);
    }

    public Board updateBoard(Long boardId, String boardName, String boardDescription) {
        Board board = boardRepository.findById(boardId).orElse(null);
        board.setBoard_name(boardName);
        board.setBoard_description(boardDescription);
        return boardRepository.save(board);
    }
    public void deleteBoard(Long id){
        Board board = boardRepository.findById(id).orElse(null);
        boardRepository.delete(board);
    }
}
