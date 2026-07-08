package com.kamel.board.dto;

import com.kamel.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;


/**
 * 게시글 수정 요청 DTO
 */
@Getter
@Builder
public class BoardUpdateRequestDto {

    @NotNull(message = "카테고리를 선택해주세요.")
    private Long categoryId; // 카테고리 ID

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 200, message = "제목은 200자 이하로 입력해주세요.")
    private String title; // 제목

    @NotBlank(message = "내용을 입력해주세요.")
    private String content; // 내용

    /**
     * 이 요청 DTO를 {@link Board} 엔티티로 변환한다.
     *
     * @return 변환된 엔티티
     */
    public Board toEntity() {
        return Board.builder()
                .categoryId(this.getCategoryId())
                .title(this.getTitle())
                .content(this.getContent())
                .build();
    }
}