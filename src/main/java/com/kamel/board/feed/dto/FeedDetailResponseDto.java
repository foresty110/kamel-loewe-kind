package com.kamel.board.feed.dto;

import com.kamel.board.entity.Board;
import com.kamel.board.entity.Comment;
import com.kamel.board.feed.entity.Feed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 피드 상세조회 화면에 노출할 피드 응답 DTO
 */
@Getter
@Builder
public class FeedDetailResponseDto {

    private Long id; // 고유 ID
    private Long boardId; // 게시글 번호
    private String author; // 작성자
    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createdAt; // 등록일시
    private int likeCount; // 좋아요 수
    private long commentCount; // 댓글 수
    private List<Comment> commentList; // 댓글 목록
    private Long imageId; // 이미지



    /**
     * {@link Feed} 엔티티를 응답 DTO로 변환한다.
     *
     * @param feed 변환할 피드 엔티티
     * @return 변환된 응답 DTO
     */
    public static FeedDetailResponseDto from(Board board, Feed feed, List<Comment> commentList, Long imageId ) {
        return FeedDetailResponseDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .likeCount(feed.getLikeCount())
                .commentCount(commentList.size())
                .commentList(commentList)
                .imageId(imageId)
                .build();
    }
}
