package com.kamel.board.feed.controller;

import com.kamel.board.dto.AttachmentDetailResponseDto;
import com.kamel.board.dto.BoardDetailResponseDto;
import com.kamel.board.dto.CommentDetailResponseDto;
import com.kamel.board.entity.Attachment;
import com.kamel.board.entity.Board;
import com.kamel.board.entity.Comment;
import com.kamel.board.feed.dto.FeedDetailResponseDto;
import com.kamel.board.feed.dto.FeedPreviewResponseDto;
import com.kamel.board.feed.entity.Feed;
import com.kamel.board.feed.service.FeedService;
import com.kamel.board.service.AttachmentService;
import com.kamel.board.service.BoardService;
import com.kamel.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 피드 화면 렌더링을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService; // 피드 서비스
    private final BoardService boardService; // 게시글 서비스
    private final AttachmentService attachmentService; // 첨부파일 서비스
    private final CommentService commentService; // 댓글 서비스

    /**
     * 피드 목록 화면을 조회한다.
     *
     * @param model 뷰로 전달할 데이터
     * @return 피드 목록 뷰 이름
     */
    @GetMapping("/feed")
    public String getList(Model model) {

        List<FeedPreviewResponseDto> feedList = feedService.getList();

        model.addAttribute("feedList", feedList);

        return "feed-list";
    }

    /**
     * 피드 상세 화면을 조회한다.
     *
     * @param boardId 조회할 게시글 번호
     * @param model   뷰로 전달할 데이터
     * @return 피드 상세 뷰 이름
     */
    @GetMapping("/feed/{boardId}")
    public String getOne(@PathVariable Long boardId, Model model) {

        FeedDetailResponseDto responseDto = feedService.getOne(boardId);

        model.addAttribute("feedDetail", responseDto);

        return "feed-view";
    }
}
