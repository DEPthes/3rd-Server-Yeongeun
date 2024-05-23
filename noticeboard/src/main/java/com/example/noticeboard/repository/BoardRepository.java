package com.example.noticeboard.repository;

import com.example.noticeboard.entity.BaseEntity;
import com.example.noticeboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> { // <entity클래스이름, pk타입>
}
