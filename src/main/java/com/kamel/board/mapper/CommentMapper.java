package com.kamel.board.mapper;

import com.kamel.board.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 댓 관련 MyBatis 매퍼
 */
@Mapper
public interface CommentMapper {
    /**
     * 특정 게시글에 속한 댓글 전체 목록을 조회한다.
     *
     * @return 댓글 전체 목록
     */
    List<Comment> findAllByBoardId(Long seq);
}
