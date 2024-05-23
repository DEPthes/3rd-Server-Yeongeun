package com.example.noticeboard.controller;

import com.example.noticeboard.dto.BoardDTO;
import com.example.noticeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성(생성자 주입을 하기 위해)
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService; // 생성자 주입
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }
    @GetMapping("/")
    public String findAll(Model model) { // 데이터를 가져올때는 Model 객체사용
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList =  boardService.findAll(); // BoardDTO객체가 담긴 List
        model.addAttribute("boardList",boardDTOList); // 가져온 데이터를 model객체에 담는다.
        return "list";
    }
}
