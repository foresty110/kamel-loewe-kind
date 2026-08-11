package com.kamel.board.notice.dto;

import com.kamel.board.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 공지사항 상세조회 정보를 담은 응답 DTO
 */
@Getter
@Builder
public class NoticeDetailResponseDto {

    private Long id; // 고유 ID
    private Long boardId; // 게시글 번호
    private String location; // 모임 장소
    private String book; // 도서명
    private String publisher; // 출판사
    private int pageStart; // 읽을 범위 시작 페이지
    private int pageEnd; // 읽을 범위 끝 페이지
    private Long fee; // 참가비
    private String feeDescription; // 참가비 관련 설명
    private LocalDateTime meetingAt; // 모임 일시

    /**
     * {@link Notice} 엔티티를 응답 DTO로 변환한다.
     *
     * @param notice 변환할 공지사항 상세정보 엔티티
     * @return 변환된 응답 DTO
     */
    public static NoticeDetailResponseDto from(Notice notice) {
        return NoticeDetailResponseDto.builder()
                .id(notice.getId())
                .boardId(notice.getBoardId())
                .location(notice.getLocation())
                .book(notice.getBook())
                .publisher(notice.getPublisher())
                .pageStart(notice.getPageStart())
                .pageEnd(notice.getPageEnd())
                .fee(notice.getFee())
                .feeDescription(notice.getFeeDescription())
                .meetingAt(notice.getMeetingAt())
                .build();
    }
}
