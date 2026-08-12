package com.kamel.board.feed.mapper;

import com.kamel.board.feed.entity.Feed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * 피드 관련 MyBatis 매퍼
 */
@Mapper
public interface FeedMapper {

    /**
     * 게시글 번호로 피드 정보를 조회한다.
     *
     * @param boardId 조회할 게시글 번호
     * @return 피드 정보
     */
    Optional<Feed> findByBoardId(Long boardId);
}
