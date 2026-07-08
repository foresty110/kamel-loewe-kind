package com.kamel.board.mapper;

import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.entity.Board;
import com.kamel.board.service.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

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

    /**
     * 게시글 상세 정보를 조회한다.
     *
     * @param seq 상세 조회할 게시글 번호
     * @return 게시글 정보
     */
    Board findById(Long seq);

    /**
     * 게시글 존재 여부를 확인한다.
     *
     * @param seq 존재 여부를 확인할 게시글 번호
     * @return 존재하면 true
     */
    boolean existsById(Long seq);

    /**
     * 게시글 조회수를 1 증가시킨다.
     *
     * @param seq 조회수를 증가시킬 게시글 번호
     */
    void increaseViewCount(Long seq);
}
