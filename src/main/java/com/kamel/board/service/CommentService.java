package com.kamel.board.service;

import com.kamel.board.entity.Comment;
import com.kamel.board.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 댓글 관련 비즈니스 로직을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper; // 댓글 매퍼
    private final BoardService boardService; // 게시글 서비스

    /**
     * 게시글에 속한 댓글 전체 목록을 조회한다.
     *
     * @param seq 게시글 ID
     * @return 댓글 목록
     */
    public List<Comment> getAll(Long seq) {
        boardService.validateExists(seq);
        return commentMapper.findAllByBoardId(seq);
    }

    /**
     * 게시글에 속한 댓글 전체 목록을 조회한다.
     *
     * @param seq 게시글 ID
     * @return 댓글 목록
     */
    public Comment create(Long seq, Comment comment) {
        boardService.validateExists(seq);
        comment.setBoardId(seq);
        commentMapper.insert(comment);

        Comment createComment = commentMapper.findById(comment.getId());
        return createComment;
    }
}
