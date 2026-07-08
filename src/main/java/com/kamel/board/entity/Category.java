package com.kamel.board.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * category 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
public class Category {
    private Long id; // 카테고리 ID
    private String name; // 카테고리명
}
