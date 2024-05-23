package com.example.noticeboard.entity;

import com.example.noticeboard.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id // pk컬럼 지정, 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql기준으로 auto_increment할 수 있게 함.
    private Long id;

    @Column(length = 20, nullable = false) // 크기가 20이고, null을 허용하지 않음.
    private String boardWriter;

    @Column // 지정을 안하면 크기가 255이고, null을 허용하는 상태.
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        // DTO에 담긴 값을 Entity로 옮겨담는 작업
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
