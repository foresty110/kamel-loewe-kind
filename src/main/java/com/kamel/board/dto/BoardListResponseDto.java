package com.kamel.board.dto;

import com.kamel.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 게시판 목록 화면 전용 응답 DTO
 */
@Getter
@Builder
public class BoardListResponseDto {

    private Long seq; // 게시글 번호
    private String categoryName; // 카테고리명
    private String author; // 작성자
    private String title; // 제목
    private int viewCount; // 조회수
    private LocalDateTime createdAt; // 등록일시
    private LocalDateTime updatedAt; // 수정일시
    private boolean hasAttachment; // 첨부파일 존재 여부

    /**
     * 서비스 계층에서 전달받은 {@link Board} 엔티티를 프론트와 약속된 형태의 DTO로 만든다.
     *
     * @param board 변환할 게시글 엔티티
     * @return 변환된 응답 DTO
     */
    public static BoardListResponseDto from(Board board) {
        return BoardListResponseDto.builder()
                .seq(board.getSeq())
                .title(board.getTitle())
                .author(board.getAuthor())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
