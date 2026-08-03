package com.kamel.board.service;

import com.kamel.board.entity.Category;
import com.kamel.board.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 카테고리 관련 비즈니스 로직을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper; // 카테고리 매퍼

    /**
     * 등록된 카테고리 전체 목록을 조회한다.
     *
     * @return 카테고리 전체 목록
     */
    public List<Category> getAll(){
        return categoryMapper.findAll();
    }

    /**
     * 카테고리 단건을 조회한다.
     *
     * @return 카테고리 단건 데이터
     */
    public Category getOne(Long categoryId) {
        return categoryMapper.findById(categoryId);
    }

    /**
     * 카테고리 존재 여부를 확인한다.
     *
     * @throws IllegalArgumentException 존재하지 않는 카테고리 번호인 경우
     */
    public void validateExists(Long categoryId) {
        if (!categoryMapper.existsById(categoryId)) {
            throw new IllegalArgumentException("카테고리가 존재하지 않습니다.");
        }
    }
}
