package com.kamel.board.service;

import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시글 관련 비즈니스 로직을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper; // 게시글 매퍼

    /**
     * 검색 조건에 맞는 게시글 목록을 조회한다.
     *
     * @param boardSearchCondition 카테고리/검색어/등록일 범위 등 검색 조건
     * @return 조건에 맞는 게시글 목록
     */
    public List<BoardListResponseDto> search(BoardSearchCondition boardSearchCondition){
        return boardMapper.search(boardSearchCondition);
    }
}
