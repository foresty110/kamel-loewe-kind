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
    List<Comment> findAllByBoardId(Long id);

    /**
     * 특정 게시글에 달린 댓글 개수를 조회한다.
     *
     * @param id 게시글 번호
     * @return 댓글 개수
     */
    int countByBoardId(Long id);

    /**
     * 댓글 id로 해당 댓글을 조회한다.
     *
     * @return 댓글 엔티티
     */
    Comment findById(Long commentId);

    /**
     * 댓글을 생성한다.
     *
     * @param comment 생성할 댓글 정보
     */
    void insert(Comment comment);
}
