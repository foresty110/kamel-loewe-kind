package com.kamel.board.dto;

import com.kamel.board.entity.Category;
import lombok.Builder;
import lombok.Getter;

/**
 * 카테고리 드롭다운 등 화면에 노출할 카테고리 응답 DTO
 */
@Builder
@Getter
public class CategoryResponseDto {
    private Long id; // 카테고리 번호
    private String name; // 카테고리명

    /**
     * {@link Category} 엔티티를 응답 DTO로 변환한다.
     *
     * @param category 변환할 카테고리 엔티티
     * @return 변환된 응답 DTO
     */
    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder().
                id(category.getId()).
                name(category.getName()).
                build();
    }
}
