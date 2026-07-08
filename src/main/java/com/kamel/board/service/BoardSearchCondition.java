package com.kamel.board.service;

import lombok.Builder;

import java.time.LocalDate;

/**
 * 게시글 목록 조회에 쓰이는 검색 조건
 */
@Builder
public class BoardSearchCondition {
    private String keyword; // 제목,작성자,내용 통합 검색어
    private Long categoryId; // 카테고리 ID
    private LocalDate startDate; // 등록일 검색 범위의 시작일
    private LocalDate endDate; // 등록일 검색 범위의 종료일
}
