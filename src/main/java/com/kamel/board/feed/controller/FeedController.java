package com.kamel.board.feed.controller;

import com.kamel.board.entity.Board;
import com.kamel.board.feed.dto.*;
import com.kamel.board.feed.service.FeedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 피드 화면 렌더링을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService; // 피드 서비스

    /**
     * 피드 등록 폼 화면을 조회한다.
     *
     * @return 피드 등록 뷰 이름
     */
    @GetMapping("/feed/new")
    public String write() {

        return "feed-write";
    }

    /**
     * 피드 게시글 등록을 요청한다.
     *
     * @param requestDto 등록할 피드 게시글 정보
     * @return 등록된 게시글 번호를 담은 응답
     */
    @PostMapping("/feed")
    public ResponseEntity<FeedCreateResponseDto> create(@Valid @RequestBody FeedCreateRequestDto requestDto) {

        Board board = feedService.create(requestDto.toEntity(), requestDto.getImageId());
        FeedCreateResponseDto responseDto = FeedCreateResponseDto.from(board);

        return ResponseEntity.ok(responseDto);
    }

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

    /**
     * 피드 수정 화면을 조회한다.
     *
     * @param boardId 수정할 게시글 번호
     * @param model   뷰로 전달할 데이터
     * @return 피드 수정 뷰 이름
     */
    @GetMapping("/feed/{boardId}/edit")
    public String edit(@PathVariable Long boardId, Model model) {

        FeedEditResponseDto responseDto = feedService.edit(boardId);

        model.addAttribute("FeedEdit", responseDto);

        return "feed-edit";
    }

    /**
     * 피드 게시글 수정을 요청한다.
     *
     * @param boardId    수정 대상 피드 게시글 번호
     * @param requestDto 수정할 피드 변경 정보
     * @return 응답 본문 없는 성공 응답
     */
    @PutMapping("/feed/{boardId}")
    public ResponseEntity<Void> update(@PathVariable Long boardId,
                                       @Valid @RequestBody FeedUpdateRequestDto requestDto) {

        feedService.update(boardId, requestDto.toEntity(),requestDto.getNewImageId(),requestDto.getRemoveImageId());
        return ResponseEntity.noContent().build();
    }

    /**
     * 피드 게시글 삭제를 요청한다.
     *
     * @param boardId    삭제 대상 피드 게시글 번호
     * @param requestDto 피드 삭제 조건
     * @return 응답 본문 없는 성공 응답
     */
    @DeleteMapping("/feed/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable Long boardId,
                                       @Valid @RequestBody FeedDeleteRequestDto requestDto) {

        feedService.delete(boardId,requestDto.toDeleteCondition());
        return ResponseEntity.noContent().build();
    }
}