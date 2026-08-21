package com.kamel.board.feed.dto;

import com.kamel.board.entity.Board;
import com.kamel.board.entity.BoardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/**
 * 새로 만들 피드 게시글의 정보를 담은 요청 DTO
 */
@Getter
@Builder
public class FeedCreateRequestDto {

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
    @Size(max = 200, message = "제목은 200자 이하로 입력해주세요.")
    private String title; // 제목

    @NotBlank(message = "내용을 입력해주세요.")
    private String content; // 내용

    private Long imageId; // 대표 이미지로 연결할 첨부파일 번호

    /**
     * 이 요청 DTO를 {@link Board} 엔티티로 변환한다.
     *
     * @return 변환된 엔티티
     */
    public Board toEntity() {
        return Board.builder()
                .boardType(BoardType.FEED)
                .author(this.author)
                .password(this.password)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
