package com.kamel.board.notice.controller;

import com.kamel.board.dto.AttachmentDetailResponseDto;
import com.kamel.board.dto.BoardDetailResponseDto;
import com.kamel.board.dto.CommentDetailResponseDto;
import com.kamel.board.entity.Attachment;
import com.kamel.board.entity.Board;
import com.kamel.board.entity.Category;
import com.kamel.board.entity.Comment;
import com.kamel.board.notice.dto.NoticeDetailResponseDto;
import com.kamel.board.notice.entity.Notice;
import com.kamel.board.notice.service.NoticeService;
import com.kamel.board.service.AttachmentService;
import com.kamel.board.service.BoardService;
import com.kamel.board.service.CategoryService;
import com.kamel.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 게시판 화면 렌더링을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final CategoryService categoryService; // 카테고리 서비스
    private final BoardService boardService; // 게시글 서비스
    private final NoticeService noticeService; // 공지사항 서비스
    private final AttachmentService attachmentService; // 첨부파일 서비스
    private final CommentService commentService; // 댓글 서비스

    /**
     * 공시사항 상세 화면을 조회한다.
     *
     * @param boardId 조회할 게시글 번호
     * @param model   뷰로 전달할 데이터
     * @return 게시판 상세 뷰 이름
     */
    @GetMapping("/notice/{boardId}")
    public String getDetail(@PathVariable Long boardId, Model model) {

        Board board = boardService.getDetail(boardId);
        Notice notice = noticeService.getDetail(boardId);
        Category category = categoryService.getOne(board.getCategoryId());
        List<Attachment> attachmentList = attachmentService.findAllByBoardId(boardId);
        List<Comment> commentList = commentService.getAll(boardId);

        model.addAttribute("boardDetail", BoardDetailResponseDto.from(board));
        model.addAttribute("noticeDetail", NoticeDetailResponseDto.from(notice));
        model.addAttribute("categoryName", category.getName());
        model.addAttribute("commentList",
                commentList.stream().map(CommentDetailResponseDto::from).toList());
        model.addAttribute("attachmentList",
                attachmentList.stream().map(AttachmentDetailResponseDto::from).toList());

        return "notice-view";
    }
}
