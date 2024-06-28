package com.example.noticeboard.service;

import com.example.noticeboard.dto.BoardDTO;
import com.example.noticeboard.entity.BoardEntity;
import com.example.noticeboard.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    private BoardDTO boardDTO;
    private BoardEntity boardEntity;

    @BeforeEach
    void setUp() {
        boardDTO = new BoardDTO();
        boardDTO.setId(1L);
        boardDTO.setBoardWriter("Test Writer");
        boardDTO.setBoardPass("password");
        boardDTO.setBoardTitle("Test Title");
        boardDTO.setBoardContents("Test Contents");
        boardDTO.setBoardHits(0);
        boardDTO.setBoardCreatedTime(LocalDateTime.now());
        boardDTO.setBoardUpdatedTime(LocalDateTime.now());

        boardEntity = BoardEntity.toSaveEntity(boardDTO);
    }

    @Test
    void save() {
        when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);

        boardService.save(boardDTO);

        verify(boardRepository, times(1)).save(any(BoardEntity.class));
    }

    @Test
    void findAll() {
        List<BoardEntity> boardEntities = new ArrayList<>();
        boardEntities.add(boardEntity);

        when(boardRepository.findAll()).thenReturn(boardEntities);

        List<BoardDTO> boardDTOList = boardService.findAll();

        assertEquals(1, boardDTOList.size());
        assertEquals(boardDTO.getBoardTitle(), boardDTOList.get(0).getBoardTitle());
        verify(boardRepository, times(1)).findAll();
    }

    @Test
    void updateHits() {
        doNothing().when(boardRepository).updateHits(1L);

        boardService.updateHits(1L);

        verify(boardRepository, times(1)).updateHits(1L);
    }

    @Test
    void findById() {
        when(boardRepository.findById(1L)).thenReturn(Optional.of(boardEntity));

        BoardDTO foundBoardDTO = boardService.findById(1L);

        assertNotNull(foundBoardDTO);
        assertEquals(boardDTO.getBoardTitle(), foundBoardDTO.getBoardTitle());
        verify(boardRepository, times(1)).findById(1L);
    }

    @Test
    void update() {
        when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);
        when(boardRepository.findById(1L)).thenReturn(Optional.of(boardEntity));

        BoardDTO updatedBoardDTO = boardService.update(boardDTO);

        assertNotNull(updatedBoardDTO);
        assertEquals(boardDTO.getBoardTitle(), updatedBoardDTO.getBoardTitle());
        verify(boardRepository, times(1)).save(any(BoardEntity.class));
    }

    @Test
    void delete() {
        doNothing().when(boardRepository).deleteById(1L);

        boardService.delete(1L);

        verify(boardRepository, times(1)).deleteById(1L);
    }
}
