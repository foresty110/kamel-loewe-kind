package com.kamel.board.mapper;

import com.kamel.board.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 첨부파일 관련 MyBatis 매퍼
 */
@Mapper
public interface AttachmentMapper {

    /**
     * 첨부파일 데이터 쓰기
     *
     * @param attachment 생성할 첨부파일 정보
     */
    void insert(Attachment attachment);

    /**
     * 첨부파일 정보를 조회한다.
     *
     * @param id 상세 조회할 첨부파일 번호
     * @return 첨부파일 정보
     */
    Attachment findById(Long id);

    /**
     * 첨부파일 목록을 게시글에 연결한다.
     *
     * @param boardId 연결할 게시글 번호
     * @param attachmentIds 연결할 첨부파일 번호 목록
     */
    void linkToBoard(Long boardId,List<Long> attachmentIds);

    /**
     * 첨부파일 존재 여부를 확인한다
     *
     * @param attachmentId 확인할 첨부파일 번호
     */
    boolean existsById(Long attachmentId);

    /**
     * 게시글에 속한 첨부파일 목록을 조회한다.
     *
     * @param boardId 조회할 게시글 번호
     * @return 게시글에 속한 첨부파일 목록
     */
    List<Attachment> findAllByBoardId(Long boardId);

    /**
     * id 목록에 해당하는 첨부파일을 삭제한다.
     *
     * @param attachmentIds 삭제할 첨부파일 번호 목록
     */
    void deleteByIds(List<Long> attachmentIds);
}
