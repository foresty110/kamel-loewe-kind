package com.kamel.board.feed.service;

import com.kamel.board.entity.Attachment;
import com.kamel.board.entity.Board;
import com.kamel.board.feed.dto.FeedPreviewResponseDto;
import com.kamel.board.feed.entity.Feed;
import com.kamel.board.feed.mapper.FeedMapper;
import com.kamel.board.service.AttachmentService;
import com.kamel.board.service.BoardService;
import com.kamel.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 피드 관련 비즈니스 로직을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class FeedService {

    private static final String BOARD_TYPE = "FEED"; // 피드 게시판 타입
    private static final int PAGE_SIZE = 20; // 한 번에 불러오는 피드 개수

    private final FeedMapper feedMapper; // 피드 매퍼
    private final BoardService boardService; // 게시글 서비스
    private final CommentService commentService; // 댓글 서비스
    private final AttachmentService attachmentService; // 첨부파일 서비스

    /**
     * 최신순으로 피드 목록을 조회한다.
     *
     * @return 피드 미리보기 목록
     */
    public List<FeedPreviewResponseDto> getList() {

        // 게시글 가져오기
        List<Board> boardList = boardService.getListByType(BOARD_TYPE, PAGE_SIZE);

        List<FeedPreviewResponseDto> responseList = new ArrayList<>();
        for (Board board : boardList) {
            Feed feed = feedMapper.findByBoardId(board.getId())
                    .orElseThrow(() -> new IllegalArgumentException("피드 게시글이 존재하지 않습니다."));

            // 피드에 달린 댓글 개수 가져오기
            int commentCount = commentService.countByBoardId(board.getId());

            // 피드 게시글은 기획상 이미지가 최대 1개라 첫 번째 첨부파일만 사용
            Attachment attachment = attachmentService.findAllByBoardId(board.getId()).stream()
                    .findFirst()
                    .orElse(null);

            responseList.add(FeedPreviewResponseDto.from(board, feed, commentCount, attachment));
        }

        return responseList;
    }
}
