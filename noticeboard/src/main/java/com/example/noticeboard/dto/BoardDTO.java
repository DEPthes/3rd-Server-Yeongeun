package com.example.noticeboard.dto;

import com.example.noticeboard.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

// DTO (Data Transfer Object) 데이터를 전달하는 객체
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개 변수로 하는 생성자
public class BoardDTO {
    private Long id; // 아이디
    private String boardWriter; // 작성자
    private String boardPass; // 작성 비밀번호
    private String boardTitle; // 게시글 제목
    private String boardContents;  // 게시글 내용
    private int boardHits; // 조회수
    private LocalDateTime boardCreatedTime; // 게시글 작성시간
    private LocalDateTime boardUpdatedTime; // 게시글 수정시간

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        // Entity를 DTO로 변환
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }
}