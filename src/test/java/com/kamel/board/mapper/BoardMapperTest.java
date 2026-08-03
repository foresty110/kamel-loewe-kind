package com.kamel.board.mapper;

import com.kamel.board.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("게시글 정보가 DB에 저장된다")
    void givenBoard_whenInsert_thenBoardIsCreate(){
        // given: 게시글 임의 데이터로 생성
        Board board = Board.builder()
                .categoryId(1L).author("작성자명").password("1234")
                .title("제목").content("내용").build();

        // when: DB에 게시글 저장
        boardMapper.insert(board);

        // then: 저장한 데이터가 일치하는지 확인한다.
        Board saved = boardMapper.findById(board.getId());
        assertThat(saved.getTitle()).isEqualTo("제목");
    }

    @Test
    @DisplayName("게시글 삭제를 성공한다")
    void givenExistingBoard_whenDelete_thenBoardIsRemoved() {

        // given: 임의의 게시글 1건을 테이블에 저장한다.
        jdbcTemplate.update(
                "INSERT INTO board (id, category_id, author, password, title, content, view_count) " +
                        "VALUES (9999, 1, '삭제될 작성자명', '삭제될 비밀번호', '삭제될 제목', '삭제될 내용', 1)");

        // when: 게시글 삭제
        boardMapper.delete(9999L);
        //
        //Board deletedBoard = boardService.delete(9999L);

        // then: 삭제한 게시글 ID로 조회하여 결과 확인
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM board WHERE id = ?", Integer.class, 9999L);
        assertThat(count).isZero();
    }
}
