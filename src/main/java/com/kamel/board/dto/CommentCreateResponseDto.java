package com.kamel.board.dto;

import com.kamel.board.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글에 속한 댓글 정보를 담은 DTO
 */
@Getter
@Builder
public class CommentCreateResponseDto {

    private Long id; //댓글 ID
    private String author; //댓글 작성자
    private String content; //내용
    private LocalDateTime createdAt; //댓글 생성일시

    /**
     * {@link Comment} 엔티티를 응답 DTO로 변환한다.
     *
     * @param comment 변환할 댓글 엔티티
     * @return 변환된 응답 DTO
     */
    public static CommentCreateResponseDto from(Comment comment) {
        return CommentCreateResponseDto.builder()
                .id(comment.getId())
                .author(comment.getAuthor())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
