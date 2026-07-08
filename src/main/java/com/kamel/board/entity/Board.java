package com.kamel.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * board 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
@Builder
public class Board {

    private Long seq; // 게시글 번호
    private Long categoryId; // 카테고리 ID
    private String author; // 작성자
    private String password; // 비밀번호 해시
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime createdAt; // 등록일시
    private LocalDateTime updatedAt; // 수정일시

    public void update(Long categoryId, String title, String content, LocalDateTime updatedAt) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
    }
}
