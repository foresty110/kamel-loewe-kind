package com.kamel.board.dto;

import com.kamel.board.service.BoardDeleteCondition;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

/**
 * 삭제할 게시글의 비밀번호를 담은 요청 DTO
 */
@Getter
@Builder
public class BoardDeleteRequestDto {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password; //비밀번호

    /**
     * DTO를 서비스 계층에 직접 전달하지 않도록 변환하는 매퍼역할의 매서드
     *
     * @return 변환된 삭제 조건
     */
    public BoardDeleteCondition toDeleteCondition()
    {
        return BoardDeleteCondition.builder()
                .password(this.password)
                .build();
    }
}
