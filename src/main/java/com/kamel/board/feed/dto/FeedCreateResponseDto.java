package com.kamel.board.feed.dto;

import com.kamel.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

/**
 * 피드 게시글 생성 후 프론트에 전달이 필요한 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class FeedCreateResponseDto {

    private Long boardId; // 새로 생성한 게시글의 번호

    public static FeedCreateResponseDto from(Board board) {
        return FeedCreateResponseDto.builder()
                .boardId(board.getId())
                .build();
    }
}
