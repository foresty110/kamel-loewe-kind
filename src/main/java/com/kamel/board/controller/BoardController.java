package com.kamel.board.controller;

import com.kamel.board.dto.*;
import com.kamel.board.entity.Board;
import com.kamel.board.service.BoardService;
import com.kamel.board.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getList(@RequestBody BoardListRequestDto requestDto, Model model) {

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
    public String getDetail(@PathVariable Long seq, Model model) {

        BoardDetailResponseDto boardDetail = BoardDetailResponseDto.from(boardService.getDetail(seq));
        model.addAttribute("boardDetail", boardDetail);
        return "board-view";
    }

    /**
     * 게시글 수정 화면을 조회한다.
     *
     * @param seq   수정할 게시글 번호
     * @param model 뷰로 전달할 데이터
     * @return 게시판 상세 뷰 이름
     */
    @GetMapping("/board/{seq}/edit")
    public String edit(@PathVariable Long seq, Model model) {

        BoardEditResponseDto responseDto = BoardEditResponseDto.from(boardService.edit(seq));
        model.addAttribute("boardDetail", responseDto);
        return "board-edit";
    }

    /**
     * 게시글을 수정 요청한다.
     *
     * @param seq   수정할 게시글 번호
     * @param model 뷰로 전달할 데이터
     * @return 게시판 상세 뷰 이름
     */
    @PutMapping("/board/{seq}")
    public String update(@PathVariable Long seq, @Valid @RequestBody BoardUpdateRequestDto requestDto, Model model) {

        Board board = boardService.update(seq,requestDto.toEntity());
        BoardUpdateResponseDto responseDto = BoardUpdateResponseDto.from(board);

        model.addAttribute("boardDetail", responseDto);
        return "board-view";
    }

    /**
     * 게시글 삭제를 요청한다.
     *
     * @param seq   수정할 게시글 번호
     * @param model 뷰로 전달할 데이터
     * @return 게시판 목록 뷰 이름
     */
    @DeleteMapping("/board/{seq}")
    public String delete(@PathVariable Long seq,Model model){

        Board board = boardService.delete(seq);
        BoardDeleteResponseDto responseDto = BoardDeleteResponseDto.from(board);

        model.addAttribute("boardDetail", responseDto);
        return "board-list";
    }
}
