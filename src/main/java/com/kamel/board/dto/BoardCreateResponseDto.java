package com.kamel.board.dto;

import com.kamel.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

/**
 * 게시판 생성 후 프론트에 전달이 필요한 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class BoardCreateResponseDto {

    private Long boardId; // 새로 생성한 게시글의 번호

    public static BoardCreateResponseDto from(Board board) {
        return BoardCreateResponseDto.builder()
                .boardId(board.getId())
                .build();
    }
}
