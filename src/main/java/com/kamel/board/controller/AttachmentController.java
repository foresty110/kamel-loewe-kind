package com.kamel.board.controller;

import com.kamel.board.dto.AttachmentUploadResponseDto;
import com.kamel.board.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/attachment")
    public ResponseEntity<AttachmentUploadResponseDto> upload(@RequestParam MultipartFile file) {
        AttachmentUploadResponseDto responseDto =
                AttachmentUploadResponseDto.from(attachmentService.save(file));
        return ResponseEntity.ok(responseDto);
    }
}