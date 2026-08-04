package com.kamel.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * attachment 테이블과 1:1로 대응하는 엔티티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private Long id; //첨부파일 ID
    private Long boardId; //첨부파일이 속한 게시글 ID
    private String originalFileName; //사용자가 업로드한 파일 이름
    private String storedPath; //상대 경로
    private String contentType; //첨부파일 확장자
    private long size; //첨부파일 크기
    private LocalDateTime createdAt; //등록일시
}
