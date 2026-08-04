package com.kamel.board.service;

import com.kamel.board.entity.Attachment;
import com.kamel.board.mapper.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 첨부파일 관련 비즈니스 로직을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class AttachmentService {

    private static final Set<String> ALLOWED_EXT = Set.of("jpg", "jpeg", "png", "pdf"); //업로드 가능한 확장자 목록
    private final AttachmentMapper attachmentMapper; //첨부파일 매퍼

    @Value("${file.upload-dir}")
    private String uploadDir; //로컬 업로드 저장 경로

    /**
     * 첨부파일 업로드하고 메타데이터를 저장한다.
     *
     * @param file 업로드 대상 첨부파일 번호
     * @return 업로드한 게시글 엔티티
     */
    @Transactional
    public Attachment save(MultipartFile file) {
        //비어있는 파일 예외처리
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }

        //파일명에서 경로관련 문자 제외하고 추출하여 원본 파일명 저장
        String originalName = StringUtils.cleanPath(file.getOriginalFilename());
        //파일명에서 확장자만 추출
        String ext = StringUtils.getFilenameExtension(originalName);

        //허용되지 않는 확장자 예외처리
        if (ext == null || !ALLOWED_EXT.contains(ext.toLowerCase())) {
            throw new IllegalArgumentException("허용되지 않는 확장자: " + ext);
        }

        //오늘 날짜를 이용해 폴더 경로로 사용할 문자열 만들기
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        //새로운 파일명 만들기 (중복 파일명,경로 조작 등과 같은 위험 예방)
        String storedName = UUID.randomUUID() + "." + ext.toLowerCase();
        //폴더 경로와 새로운 파일명으로 상대경로 만들기
        String storedPath = datePath + "/" + storedName;

        Path target = Paths.get(uploadDir) // 문자열을 '경로객체'로 변환
                .resolve(storedPath); // '/' 구분자 정제

        try {
            Files.createDirectories(target.getParent()); //폴더 경로를 전달하고 폴더 만들기
            try (InputStream in = file.getInputStream()) { //파일의 바이트를 읽을 수 있는 스트림 가져오기
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                //in: 업로드된 파일의 내용 중 읽은 것
                //target: 읽은 파일을 옮길 경로
                //StandardCopyOption: 이미 읽은 것이라면 덮어씌우는 옵션
            }
        } catch (IOException e) {
            throw new UncheckedIOException("파일 저장 실패", e);
        }

        //테이블에 저장할 메타데이터 객체 생성
        Attachment attachment = Attachment.builder()
                .originalFileName(originalName)
                .storedPath(storedPath)
                .contentType(file.getContentType())
                .size(file.getSize())
                .build();

        //첨부파일 데이터 쓰기
        attachmentMapper.insert(attachment);

        return attachmentMapper.findById(attachment.getId());
    }

    /**
     * 첨부파일과 게시글을 연결한다
     *
     * @param boardId 게시글 번호
     * @param attachmentIds 첨부파일 번호 목록
     */
    @Transactional
    public void linkToBoard(Long boardId, List<Long> attachmentIds) {
        //첨부파일이 비어있는 경우
        if (attachmentIds == null || attachmentIds.isEmpty()) {
            return;
        }
        attachmentMapper.linkToBoard(boardId, attachmentIds);
    }
}