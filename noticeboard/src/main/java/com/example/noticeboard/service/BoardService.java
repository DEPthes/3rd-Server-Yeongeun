package com.example.noticeboard.service;

import com.example.noticeboard.dto.BoardDTO;
import com.example.noticeboard.entity.BoardEntity;
import com.example.noticeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service에서는
// Entity -> DTO : (Entitiy 클래스에서 작업) Controller로 부터 호출을 받을 때
// DTO -> Entity : (DTO 클래스에서 작업) Repository로 넘겨줄때. Data를 조회할때는 Repository로 부터 entity를 조회한다.
// 로 변환하는 작업을 한다.

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository; // 생성자 주입
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); //insert를 DB에다 하기 위해
        // save매서드는 기본적으로 매개변수를 entity 클래스 타입으로 받게 되어있음.
    }
    public List<BoardDTO> findAll(){
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}
