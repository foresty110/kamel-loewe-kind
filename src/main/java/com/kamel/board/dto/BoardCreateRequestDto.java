package com.kamel.board.dto;

import com.kamel.board.entity.Board;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 새로 만들 게시글의 정보를 담은 요청 DTO
 */
@Getter
@Builder
public class BoardCreateRequestDto {

    @NotNull(message = "카테고리 ID는 필수 값입니다.")
    @Positive(message = "잘못된 카테고리 ID입니다.")
    private Long categoryId; // 카테고리 ID

    @NotBlank(message = "작성자명을 입력해주세요.")
    @Size(max = 20, message = "작성자명은 20자 이하로 입력해주세요.")
    private String author; // 작성자

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 8~20자의 영문, 숫자, 특수문자(@$!%*#?&)를 포함해야 합니다."
    )
    private String password; // 비밀번호

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 200, message = "제목을 200자 이하로 입력해주세요.")
    private String title; // 제목

    @NotBlank(message = "내용을 입력해주세요.")
    private String content; // 내용

    private List<Long> attachmentIds; //첨부파일 목록

    /**
     * 이 요청 DTO를 {@link Board} 엔티티로 변환한다.
     *
     * @return 변환된 엔티티
     */
    public Board toEntity() {
        return Board.builder()
                .categoryId(this.getCategoryId())
                .author(this.getAuthor())
                .password(this.getPassword())
                .title(this.getTitle())
                .content(this.getContent())
                .build();
    }
}
