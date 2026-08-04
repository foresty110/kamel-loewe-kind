package com.kamel.board.dto;

import com.kamel.board.entity.Attachment;
import lombok.Builder;
import lombok.Getter;

/**
 * 첨부파일 업로드 후 프론트에 전달이 필요한 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class AttachmentUploadResponseDto {
    private Long id; //첨부파일 ID
    private String originalFileName; //사용자가 올린 이름
    private String contentType; //첨부파일 확장자
    private long size; //첨부파일 크기
    public static AttachmentUploadResponseDto from(Attachment attachment) {
        return AttachmentUploadResponseDto.builder()
                .id(attachment.getId())
                .originalFileName(attachment.getOriginalFileName())
                .contentType(attachment.getContentType())
                .size(attachment.getSize())
                .build();
    }
}
