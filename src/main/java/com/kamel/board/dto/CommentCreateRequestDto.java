package com.kamel.board.dto;

import com.kamel.board.entity.Comment;
import com.kamel.board.service.BoardSearchCondition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * 게시글 생성 요청 DTO
 */
@Getter
@Builder
public class CommentCreateRequestDto {
    @NotBlank(message = "작성자명을 입력해주세요.")
    @Size(max = 20, message = "작성자명은 20자 이하로 입력해주세요.")
    private String author; // 작성자

    @NotBlank(message = "내용을 입력해주세요.")
    private String content; // 내용

    /**
     * DTO를 서비스 계층에 직접 전달하지 않도록 변환하는 매퍼역할의 매서드
     *
     * @return 변환된 삭제 조건
     */
    public Comment toEntity() {
        return Comment.builder()
                .author(this.author)
                .content(this.content)
                .build();
    }
}
