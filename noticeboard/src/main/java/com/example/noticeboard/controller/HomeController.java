package com.example.noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 시작 주소 Mapping
    @GetMapping("/")
    public String index(){
        return "index"; // index (시작) 페이지 띄우기
    }

}
