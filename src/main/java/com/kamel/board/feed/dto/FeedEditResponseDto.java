package com.kamel.board.feed.dto;

import com.kamel.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 피드 수정 화면에 노출하기 위한 수정 전 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class FeedEditResponseDto {

    private Long id; // 게시글 번호
    private String author; // 작성자
    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createdAt; // 등록일시
    private Long imageId; // 대표 이미지

    /**
     * {@link Board} 엔티티를 응답 DTO로 변환한다.
     *
     * @param board   변환할 게시글 엔티티
     * @param imageId 대표 이미지 첨부파일 번호
     * @return 변환된 응답 DTO
     */
    public static FeedEditResponseDto from(Board board, Long imageId) {
        return FeedEditResponseDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .imageId(imageId)
                .build();
    }
}
