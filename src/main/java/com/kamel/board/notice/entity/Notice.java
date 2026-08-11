package com.kamel.board.notice.entity;

import lombok.*;

import java.time.LocalDateTime;

/**
 * board_notice 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {
    private Long id; // 고유 ID
    private Long boardId; // 게시글 번호
    private String location; // 모임 장소
    private String book; // 도서명
    private String publisher; // 출판사
    private int pageStart; // 읽을 범위 시작 페이지
    private int pageEnd; // 읽을 범위 끝 페이지
    private Long fee; // 참가비
    private String feeDescription; // 참가비 관련 설명
    private LocalDateTime meetingAt; // 모임 일시
}
