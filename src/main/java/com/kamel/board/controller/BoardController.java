package com.kamel.board.controller;

import com.kamel.board.dto.*;
import com.kamel.board.entity.Board;
import com.kamel.board.entity.Category;
import com.kamel.board.entity.Comment;
import com.kamel.board.service.BoardService;
import com.kamel.board.service.CategoryService;
import com.kamel.board.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final CategoryService categoryService; // 카테고리 서비스
    private final BoardService boardService; // 게시글 서비스
    private final CommentService commentService; // 댓글 서비스


    /**
     * 게시판 목록 화면을 조회한다.
     *
     * @param requestDto 카테고리/검색어/등록일 범위 등 검색 조건
     * @param model      뷰로 전달할 데이터
     * @return 게시판 목록 뷰 이름
     */
    @GetMapping("/board")
    public String getList(@ModelAttribute BoardListRequestDto requestDto, Model model) {

        List<CategoryResponseDto> categoryList = categoryService.getAll().stream()
                .map(CategoryResponseDto::from)
                .toList();

        List<BoardListResponseDto> boardList = boardService.search(requestDto.toSearchCondition());

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("boardList", boardList);
        return "board-list";
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

        Board board = boardService.getDetail(seq);
        Category category = categoryService.getOne(board.getCategoryId());
        List<Comment> commentList = commentService.getAll(seq);

        model.addAttribute("boardDetail", BoardDetailResponseDto.from(board));
        model.addAttribute("categoryName", category.getName());
        model.addAttribute("commentList",
                commentList.stream().map(CommentDetailResponseDto::from).toList());

        return "board-view";
    }

    /**
     * 게시글 생성 폼 화면을 조회한다.
     *
     * @param model 뷰로 전달할 데이터
     * @return 게시판 생성 뷰 이름
     */
    @GetMapping("/board/new")
    public String write(Model model) {

        List<CategoryResponseDto> categoryList = categoryService.getAll().stream()
                .map(CategoryResponseDto::from)
                .toList();

        model.addAttribute("categoryList", categoryList);
        return "board-create";
    }

    /**
     * 게시글 생성을 요청한다.
     *
     * @param model 뷰로 전달할 데이터
     * @return 게시판 상세 뷰 이름
     */
    @PostMapping("/board")
    public ResponseEntity<BoardCreateResponseDto> create(@Valid @RequestBody BoardCreateRequestDto requestDto, Model model) {

        Board board = boardService.create(requestDto.toEntity());
        BoardCreateResponseDto responseDto = BoardCreateResponseDto.from(board);

        return ResponseEntity.ok(responseDto);
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

        List<CategoryResponseDto> categoryList = categoryService.getAll().stream()
                .map(CategoryResponseDto::from)
                .toList();

        BoardEditResponseDto responseDto = BoardEditResponseDto.from(boardService.edit(seq));

        model.addAttribute("categoryList", categoryList);
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
    public ResponseEntity<Void> update(@PathVariable Long seq, @Valid @RequestBody BoardUpdateRequestDto requestDto, Model model) {

        Board board = boardService.update(seq, requestDto.toEntity());
        BoardUpdateResponseDto responseDto = BoardUpdateResponseDto.from(board);

        return ResponseEntity.noContent().build();
    }

    /**
     * 게시글 삭제를 요청한다.
     *
     * @param seq 수정할 게시글 번호
     * @return 게시판 목록 뷰 이름
     */
    @DeleteMapping("/board/{seq}")
    public ResponseEntity<Void> delete(@PathVariable Long seq,
                                       @Valid @RequestBody BoardDeleteRequestDto requestDto) {

        boardService.delete(seq, requestDto.toDeleteCondition());

        return ResponseEntity.noContent().build();
    }
}
