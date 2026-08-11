package com.kamel.board.notice.service;

import com.kamel.board.notice.entity.Notice;
import com.kamel.board.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 공지사항 관련 비즈니스 로직을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper; // 공지사항 매퍼

    /**
     * 게시글 번호로 공지사항을 조회한다.
     *
     * @param id 상세 조회할 게시글 번호
     * @return 조회수가 반영된 게시글 정보
     * @throws IllegalArgumentException 존재하지 않는 게시글 번호인 경우
     */
    @Transactional
    public Notice getDetail(Long id) {

        return noticeMapper.findByBoardId(id)
                .orElseThrow(() -> new IllegalArgumentException("공지사항 게시글이 존재하지 않습니다."));
    }
}
