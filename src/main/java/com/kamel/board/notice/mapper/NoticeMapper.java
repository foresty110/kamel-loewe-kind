package com.kamel.board.notice.mapper;

import com.kamel.board.notice.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * 공지사항 관련 MyBatis 매퍼
 */
@Mapper
public interface NoticeMapper {

    /**
     * 게시글 번호로 공지사항을 조회한다.
     *
     * @param boardId 조회할 게시글 번호
     * @return 공지사항 정보
     */
    Optional<Notice> findByBoardId(Long boardId);
}
