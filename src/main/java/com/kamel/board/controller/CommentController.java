package com.kamel.board.controller;

import com.kamel.board.dto.CommentCreateRequestDto;
import com.kamel.board.dto.CommentCreateResponseDto;
import com.kamel.board.entity.Comment;
import com.kamel.board.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 댓글 화면 렌더링을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    /**
     * 댓글 생성을 요청한다.
     *
     * @param model 뷰로 전달할 데이터
     * @return 게시판 상세 뷰 이름
     */
    @PostMapping("/board/{seq}/comments")
    public ResponseEntity<CommentCreateResponseDto> create(
            @PathVariable Long seq,
            @Valid @RequestBody CommentCreateRequestDto requestDto,
            Model model) {

        Comment comment = commentService.create(seq,requestDto.toEntity());
        CommentCreateResponseDto responseDto = CommentCreateResponseDto.from(comment);

        return ResponseEntity.ok(responseDto);
    }
}
