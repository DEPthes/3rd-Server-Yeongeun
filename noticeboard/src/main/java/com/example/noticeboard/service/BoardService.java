package com.example.noticeboard.service;

import com.example.noticeboard.dto.BoardDTO;
import com.example.noticeboard.entity.BoardEntity;
import com.example.noticeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service에서는
// Entity -> DTO : (Entitiy 클래스에서 작업) Controller로 부터 호출을 받을 때
// DTO -> Entity : (DTO 클래스에서 작업) Repository로 넘겨줄때. Data를 조회할때는 Repository로 부터 entity를 조회한다.
// 로 변환하는 작업을 한다.

@Service
@RequiredArgsConstructor // 생성자 주입
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        // BoardDTO를 BoardEntity로 변환
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        // BoardEntity를 데이터베이스에 저장
        boardRepository.save(boardEntity); //insert를 DB에다 하기 위해
        // save매서드는 기본적으로 매개변수를 entity 클래스 타입으로 받게 되어있음.
    }
    public List<BoardDTO> findAll(){
        // 데이터베이스에서 모든 BoardEntity를 조회
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        // BoardEntity를 BoardDTO로 변환하여 리스트에 추가
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        // BoardDTO 리스트 반환
        return boardDTOList;
    }
    @Transactional
    public void updateHits(Long id){
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id){
        boardRepository.deleteById(id);
    }
}
