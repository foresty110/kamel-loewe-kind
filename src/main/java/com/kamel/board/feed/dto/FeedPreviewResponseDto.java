package com.kamel.board.feed.dto;

import com.kamel.board.entity.Attachment;
import com.kamel.board.entity.Board;
import com.kamel.board.feed.entity.Feed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 피드 목록 화면 전용 응답 DTO
 */
@Getter
@Builder
public class FeedPreviewResponseDto {

    private Long id; // 게시글 번호
    private String author; // 작성자
    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createdAt; // 등록일시
    private int likeCount; // 좋아요 수
    private long commentCount; // 댓글 수
    private Long image; // 이미지

    /**
     * 게시글과 피드 정보를 응답 DTO로 변환한다.
     *
     * @param board        게시글 정보
     * @param feed         피드 정보
     * @param commentCount 댓글 수
     * @param attachment   이미지
     * @return 변환된 응답 DTO
     */
    public static FeedPreviewResponseDto from(Board board, Feed feed, int commentCount, Attachment attachment) {

        return FeedPreviewResponseDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .likeCount(feed.getLikeCount())
                .commentCount(commentCount)
                .image(attachment != null ? attachment.getId() : null)
                .build();
    }
}
