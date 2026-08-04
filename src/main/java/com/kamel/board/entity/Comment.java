package com.kamel.board.entity;

import lombok.*;

import java.time.LocalDateTime;

/**
 * comment 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id; // 댓글 번호
    private Long boardId; // 카테고리 ID
    private String author; // 작성자
    private String content; // 내용
    private LocalDateTime createdAt; // 등록일시
}
