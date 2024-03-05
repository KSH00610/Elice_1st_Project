package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {
    String board_name;
    String board_description;

    public BoardDto(String board_name, String board_description){
        this.board_name=board_name;
        this.board_description=board_description;
    }


    public String getBoard_name() {
        return board_name;
    }

    public String getBoard_description() {
        return board_description;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public void setBoard_description(String board_description) {
        this.board_description = board_description;
    }

    public Board toEntity(){
        return new Board(board_name,board_description);
    }
}
