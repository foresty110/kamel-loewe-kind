package com.kamel.board.service;

import com.kamel.board.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("게시글 삭제를 성공한다")
    void givenExistingBoard_whenDelete_thenBoardIsRemoved() {

        // given: 임의의 게시글 1건을 테이블에 저장한다.
        jdbcTemplate.update(
                "INSERT INTO board (id, category_id, author, password, title, content, view_count) " +
                        "VALUES (9999L, 1, '삭제될 작성자명', '삭제될 비밀번호', '삭제될 제목', '삭제될 내용', 1)");

        // when: 게시글 삭제
        //Board deletedBoard = boardService.delete(9999L,'삭제될 비밀번호');

        // then: 삭제한 게시글 ID로 조회하여 결과 확인
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM board WHERE id = ?", Integer.class, 9999L);
        assertThat(count).isZero();
    }

    @Test
    @DisplayName("게시글 생성 시 비밀번호가 암호화되어 저장된다")
    void givenRawPassword_whenCreate_thenPasswordIsEncrypted() {

        // given: 평문 비밀번호로 게시글 데이터를 만든다.
        Long categoryId = jdbcTemplate.queryForObject(
                "SELECT id FROM category WHERE name = '윤리학'", Long.class);

        String rawPassword = "1234";
        Board board = Board.builder()
                .categoryId(categoryId)
                .author("작성자명")
                .password(rawPassword)
                .title("제목")
                .content("내용")
                .build();

        // when: 게시글 생성
        Board created = boardService.create(board);

        // then: 반환된 비밀번호가 평문 그대로가 아니라 암호화된 값이다.
        assertThat(created.getPassword()).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, created.getPassword())).isTrue();

        // then: DB에 저장된 비밀번호도 평문이 아니라 암호화된 값이다.
        String storedPassword = jdbcTemplate.queryForObject(
                "SELECT password FROM board WHERE id = ?", String.class, created.getId());
        assertThat(storedPassword).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, storedPassword)).isTrue();
    }

}
