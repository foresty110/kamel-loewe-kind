package com.kamel.board.dto;

import com.kamel.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

/**
 * 게시판 삭제 후 프론트에 전달이 필요한 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class BoardDeleteResponseDto {

    private Long seq; // 게시글 번호

    public static BoardDeleteResponseDto from(Board board) {
        return BoardDeleteResponseDto.builder()
                .seq(board.getSeq())
                .build();
    }
}
