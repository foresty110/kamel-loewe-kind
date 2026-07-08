package com.kamel.board.controller;

import com.kamel.board.dto.BoardDetailResponseDto;
import com.kamel.board.dto.BoardListRequestDto;
import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.dto.CategoryResponseDto;
import com.kamel.board.service.BoardService;
import com.kamel.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 게시판 화면 렌더링을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final CategoryService categoryService; // 카테고리 드롭다운 목록 조회용 서비스
    private final BoardService boardService; // 게시글 목록 검색용 서비스

    /**
     * 게시판 목록 화면을 조회한다.
     *
     * @param requestDto 카테고리/검색어/등록일 범위 등 검색 조건
     * @param model      뷰로 전달할 데이터
     * @return 게시판 목록 뷰 이름
     */
    @GetMapping("/board")
    public String getBoardList(@RequestBody BoardListRequestDto requestDto, Model model) {

        List<CategoryResponseDto> categoryList = categoryService.getAllCateogry().stream()
                .map(CategoryResponseDto::from)
                .toList();

        List<BoardListResponseDto> boardList = boardService.search(requestDto.toSearchCondition());

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("boardList", boardList);
        return "board";
    }

    /**
     * 게시글 상세 화면을 조회한다.
     *
     * @param seq   조회할 게시글 번호
     * @param model 뷰로 전달할 데이터
     * @return 게시판 상세 뷰 이름
     */
    @GetMapping("/board/{seq}")
    public String getBoardDetail(@PathVariable Long seq, Model model) {

        BoardDetailResponseDto boardDetail = BoardDetailResponseDto.from(boardService.getBoardDetail(seq));
        model.addAttribute("boardDetail", boardDetail);
        return "board-view";
    }
}
