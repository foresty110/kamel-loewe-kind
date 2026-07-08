package com.kamel.board.dto;

import com.kamel.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 게시판 수정 화면에 노출하기 위한 수정 전 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class BoardEditResponseDto {

    private Long seq; // 게시글 번호
    private Long categoryId; // 카테고리 ID
    private String author; // 작성자
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime createdAt; // 등록일시
    private LocalDateTime updatedAt; // 수정일시

    /**
     * {@link Board} 엔티티를 응답 DTO로 변환한다.
     *
     * @param board 변환할 게시글 엔티티
     * @return 변환된 응답 DTO
     */
    public static BoardEditResponseDto from(Board board) {
        return BoardEditResponseDto.builder()
                .seq(board.getSeq())
                .categoryId(board.getCategoryId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}

