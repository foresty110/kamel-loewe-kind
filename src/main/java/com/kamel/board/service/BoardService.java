package com.kamel.board.service;

import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.entity.Board;
import com.kamel.board.mapper.BoardMapper;
import com.kamel.board.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final CategoryService categoryService; // 카테고리 서비스
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    /**
     * 검색 조건에 맞는 게시글 목록을 조회한다.
     *
     * @param boardSearchCondition 카테고리/검색어/등록일 범위 등 검색 조건
     * @return 조건에 맞는 게시글 목록
     */
    public List<BoardListResponseDto> search(BoardSearchCondition boardSearchCondition) {
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
        //게시글 존재여부 확인
        this.validateExists(seq);

        // 조회수 증가
        boardMapper.increaseViewCount(seq);

        return boardMapper.findById(seq);
    }

    /**
     * 전달받은 정보로 게시글을 생성한다.
     *
     * @param board 생성할 게시글 정보
     * @return 생성한 게시글
     * @throws IllegalArgumentException 존재하지 않는 카테고리 번호인 경우
     */
    public Board create(Board board) {

        //카데고리 존재 여부 확인
        categoryService.validateExists(board.getCategoryId());

        // 비밀번호 암호화
        board.setPassword(passwordEncoder.encode(board.getPassword()));

        // DB 저장
        boardMapper.insert(board);

        return board;
    }

    /**
     * 수정 대상 게시글의 정보를 가져온다.
     *
     * @param seq 수정 대상 게시글 번호
     * @return 수정 대상 게시글 정보
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    public Board edit(Long seq) {
        //게시글 존재여부 확인
        this.validateExists(seq);

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
        //게시글 존재여부 확인
        this.validateExists(seq);

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
    public Board delete(Long seq, BoardDeleteCondition deleteCondition) {
        // 존재하지 않는 게시글이면 예외 처리
        if (!boardMapper.existsById(seq)) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        Board deleteBoard = boardMapper.findById(seq);
        if (!passwordEncoder.matches(deleteCondition.getPassword(), deleteBoard.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        boardMapper.delete(seq);

        return deleteBoard;
    }

    /**
     * 게시글이 존재하는지 검증한다.
     *
     * @param seq 검증할 게시글 번호
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    public void validateExists(Long seq) {
        if (!boardMapper.existsById(seq)) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
    }
}
