package com.kamel.board.mapper;

import com.kamel.board.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 카테고리 관련 MyBatis 매퍼
 */
@Mapper
public interface CategoryMapper {

    /**
     * 등록된 카테고리 전체 목록을 조회한다.
     *
     * @return 카테고리 전체 목록
     */
    List<Category> findAll();
}
