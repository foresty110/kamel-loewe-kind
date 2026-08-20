package com.kamel.board.feed.service;

import com.kamel.board.entity.Board;
import com.kamel.board.entity.Comment;
import com.kamel.board.feed.dto.FeedDetailResponseDto;
import com.kamel.board.feed.dto.FeedEditResponseDto;
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
            Long imageId = attachmentService.findFirstByBoardId(board.getId());

            responseList.add(FeedPreviewResponseDto.from(board, feed, commentCount, imageId));
        }

        return responseList;
    }

    /**
     * 게시글 번호로 피드 상세정보를 조회한다.
     *
     * @param boardId 조회할 게시글 번호
     * @return 피드 상세정보
     * @throws IllegalArgumentException 존재하지 않는 피드 게시글인 경우
     */
    public FeedDetailResponseDto getOne(Long boardId) {

        // 게시글 가져오기
        Board board = boardService.getDetail(boardId);

        // 피드 상세 정보 가져오기
        Feed feed = feedMapper.findByBoardId(boardId)
                .orElseThrow(() -> new IllegalArgumentException("피드 게시글이 존재하지 않습니다."));

        // 댓글 가져오기
        List<Comment> commentList = commentService.getAll(boardId);

        // 피드 게시글은 기획상 이미지가 최대 1개라 첫 번째 첨부파일만 사용
        Long imageId = attachmentService.findFirstByBoardId(boardId);

        return FeedDetailResponseDto.from(board, feed, commentList, imageId);
    }

    /**
     * 게시글 번호로 피드 수정 화면에 필요한 정보를 조회한다.
     *
     * @param boardId 수정할 게시글 번호
     * @return 피드 수정 화면용 응답 정보
     */
    public FeedEditResponseDto edit(Long boardId) {

        // 게시글 기본 정보 가져오기
        Board board = boardService.edit(boardId);

        // 피드 게시글은 기획상 이미지가 최대 1개라 첫 번째 첨부파일만 사용
        Long imageId = attachmentService.findFirstByBoardId(boardId);

        return FeedEditResponseDto.from(board, imageId);
    }

    /**
     * 게시글 번호로 피드 게시글을 수정한다.
     *
     * @param boardId       수정 대상 게시글 번호
     * @param board         수정할 제목·내용을 담은 게시글 정보
     * @param newImageId    새로 등록할 대표 이미지 첨부파일 번호
     * @param removeImageId 제거할 기존 대표 이미지 첨부파일 번호
     */
    public void update(Long boardId, Board board, Long newImageId, Long removeImageId) {

        // 피드 화면에는 카테고리 선택 UI가 없어서 board.categoryId는 항상 null로 넘어옴.
        // boardService.update는 categoryId가 null이면 기존 값을 그대로 유지하므로 별도 처리가 필요 없음.

        // 단일 이미지 id를 boardService.update가 요구하는 목록 형태로 변환
        List<Long> newImageIds = newImageId != null ? List.of(newImageId) : List.of();
        List<Long> removeImageIds = removeImageId != null ? List.of(removeImageId) : List.of();

        // 게시글 기본 정보 수정
        boardService.update(boardId, board, newImageIds, removeImageIds);
    }
}