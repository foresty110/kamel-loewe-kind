package com.kamel.board.controller;

import com.kamel.board.dto.AttachmentUploadResponseDto;
import com.kamel.board.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 첨부파일을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    /**
     * 첨부파일 단건 업로드를 요청한다.
     *
     * @param file 업로드할 첨부파일
     * @return 저장된 첨부파일의 메타정보를 담은 응답 dto
     */
    @PostMapping("/attachment")
    public ResponseEntity<AttachmentUploadResponseDto> upload(@RequestParam MultipartFile file) {
        AttachmentUploadResponseDto responseDto =
                AttachmentUploadResponseDto.from(attachmentService.save(file));
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 첨부파일을 다운로드한다.
     *
     * 파일 실체는 응답 본문에 스트리밍으로 전달되며,
     * 헤더를 통해 브라우저에서 다운로드된다.
     *
     * @param id 첨부파일 id
     * @return 파일 리소스와 다운로드 헤더가 담긴 응답
     */
    @GetMapping("/attachment/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource file = attachmentService.getOne(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }
}