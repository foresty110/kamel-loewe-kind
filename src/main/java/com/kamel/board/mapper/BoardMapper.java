package com.kamel.board.mapper;

import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.service.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 게시글 관련 MyBatis 매퍼
 */
@Mapper
public interface BoardMapper {

    /**
     * 검색 조건과 일치하는 게시글 목록을 조회한다.
     *
     * @param condition 카테고리/검색어/등록일 범위 등 검색 조건
     * @return 조건에 맞는 게시글 목록
     */
    List<BoardListResponseDto> search(BoardSearchCondition condition);
}
