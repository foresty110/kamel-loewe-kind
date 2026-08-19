package com.kamel.board.service;

import com.kamel.board.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.Resource;

/**
 * 첨부파일 메타정보와 실제 파일 리소스를 함께 담은 조회 결과
 */
@Getter
@AllArgsConstructor
public class AttachmentResource {
    private final Attachment attachment; //첨부파일 메타정보
    private final Resource resource; //실제 파일 리소스
}
