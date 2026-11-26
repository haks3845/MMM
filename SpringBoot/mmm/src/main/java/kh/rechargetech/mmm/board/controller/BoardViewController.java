package kh.rechargetech.mmm.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardViewController {

    // 게시판 목록 화면
    @GetMapping("")
    public String listPage() {
        return "board/list";   // templates/board/list.html
    }
}