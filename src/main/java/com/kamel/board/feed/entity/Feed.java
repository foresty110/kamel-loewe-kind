package com.kamel.board.feed.entity;

import lombok.*;

/**
 * board_feed 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feed {
    private Long id; // 고유 ID
    private Long boardId; // 게시글 번호
    private int likeCount; // 좋아요 수
}
