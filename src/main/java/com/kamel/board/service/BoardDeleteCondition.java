package com.kamel.board.service;

import lombok.Builder;
import lombok.Getter;

/**
 * 게시글 삭제 가능 조건을 담은 서비스 전달용 객체
 */
@Builder
@Getter
public class BoardDeleteCondition {
    private String password; // 비밀번호
}
