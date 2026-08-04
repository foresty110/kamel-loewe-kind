package com.kamel.board.dto;

import com.kamel.board.entity.Attachment;
import lombok.Builder;
import lombok.Getter;

/**
 * 게시글 수정 시 게시글에 속한 첨부파일 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class AttachmentEditResponseDto {
    private Long id; //첨부파일 ID
    private String originalFileName; //사용자가 업로드한 파일 이름
    private String contentType; //첨부파일 확장자
    private long size; //첨부파일 크기

    /**
     * {@link Attachment} 엔티티를 응답 DTO로 변환한다.
     *
     * @param attachment 변환할 게시글 엔티티
     * @return 변환된 응답 DTO
     */
    public static AttachmentEditResponseDto from(Attachment attachment) {
        return AttachmentEditResponseDto.builder()
                .id(attachment.getId())
                .originalFileName(attachment.getOriginalFileName())
                .contentType(attachment.getContentType())
                .size(attachment.getSize())
                .build();
    }
}
