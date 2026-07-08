package com.kamel.board.dto;

import com.kamel.board.service.BoardSearchCondition;

import java.time.LocalDate;

/**
 * 게시판 목록 화면에서 넘어오는 검색 조건 요청 DTO
 */
public class BoardListRequestDto {
    private String keyword; // 제목/작성자/내용 통합 검색어
    private Long categoryId; // 카테고리 필터, 선택하지 않으면 전체 카테고리를 조회
    private LocalDate startDate; // 등록일 검색 범위의 시작일
    private LocalDate endDate; // 등록일 검색 범위의 종료일

    /**
     * DTO를 서비스 계층에 직접 전달하지 않도록 변환하는 매퍼역할의 매서드
     *
     * @return 변환된 검색 조건
     */
    public BoardSearchCondition toSearchCondition()
    {
        return BoardSearchCondition.builder()
                .keyword(this.keyword)
                .categoryId(this.categoryId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
