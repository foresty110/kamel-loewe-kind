package com.kamel.board.entity;

import lombok.*;

/**
 * category 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private Long id; // 카테고리 ID
    private String name; // 카테고리명
}
