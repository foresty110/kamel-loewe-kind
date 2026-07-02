package com.kamel.board.controller;

import com.kamel.board.mapper.BoardMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class BoardController {

    private final BoardMapper boardMapper;

    public BoardController(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("mysqlVersion", boardMapper.selectMysqlVersion());
        return "board";
    }
}
