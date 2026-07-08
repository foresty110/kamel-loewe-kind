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
    public List<Category> getAllCateogry(){
        return categoryMapper.findAll();
    }
}
