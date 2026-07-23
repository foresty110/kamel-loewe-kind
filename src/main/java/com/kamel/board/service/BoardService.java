package com.kamel.board.service;

import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.dto.BoardUpdateRequestDto;
import com.kamel.board.entity.Board;
import com.kamel.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    /**
     * 게시글 상세 정보를 조회하고 조회수를 1 증가시킨다.
     *
     * @param seq 상세 조회할 게시글 번호
     * @return 조회수가 반영된 게시글 정보
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    @Transactional
    public Board getDetail(Long seq) {

        // 존재하지 않는 게시글이면 예외 처리
        if (!boardMapper.existsById(seq)) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
        // 조회수 증가
        boardMapper.increaseViewCount(seq);

        return boardMapper.findById(seq);
    }

    /**
     * 수정 대상 게시글의 정보를 가져온다.
     *
     * @param seq 수정 대상 게시글 번호
     * @return 수정 대상 게시글 정보
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    public Board edit(Long seq) {
        // 존재하지 않는 게시글이면 예외 처리
        if (!boardMapper.existsById(seq)) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
        return boardMapper.findById(seq);
    }

    /**
     * 게시글을 수정한다.
     *
     * @param seq 수정 대상 게시글 번호
     * @return 수정 대상 게시글 정보
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    @Transactional
    public Board update(Long seq, Board board) {
        // 존재하지 않는 게시글이면 예외 처리
        if (!boardMapper.existsById(seq)) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
        Board befBoard = boardMapper.findById(seq);

        befBoard.update(
                board.getCategoryId(),
                board.getTitle(),
                board.getContent(),
                LocalDateTime.now());

        boardMapper.update(befBoard);

        return befBoard;
    }

    /**
     * 게시글을 삭제한다.
     *
     * @param seq 수정 대상 게시글 번호
     * @return 삭제 대상 게시글 정보
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    public Board delete(Long seq) {
        // 존재하지 않는 게시글이면 예외 처리
        if (!boardMapper.existsById(seq)) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        Board deleteBoard = boardMapper.findById(seq);

        boardMapper.delete(seq);

        return deleteBoard;
    }
}
