package com.kamel.board.feed.dto;

import com.kamel.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * 피드 게시글 수정 요청 DTO
 */
@Getter
@Builder
public class FeedUpdateRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password; // 등록 시 입력한 비밀번호

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 200, message = "제목은 200자 이하로 입력해주세요.")
    private String title; // 제목

    @NotBlank(message = "내용을 입력해주세요.")
    private String content; // 내용

    private Long newImageId; // 새로 등록할 대표 이미지 첨부파일 번호

    private Long removeImageId; // 제거할 기존 대표 이미지 첨부파일 번호

    /**
     * 이 요청 DTO를 {@link Board} 엔티티로 변환한다.
     *
     * @return 변환된 엔티티
     */
    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
