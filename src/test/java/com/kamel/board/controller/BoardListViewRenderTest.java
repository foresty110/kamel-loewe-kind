package com.kamel.board.controller;

import com.kamel.board.dto.BoardListResponseDto;
import com.kamel.board.dto.CategoryResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * delete()/update()는 DELETE/PUT이라 Tomcat이 JSP forward에 405를 던지는 별개의 인프라
 * 이슈가 있어(이 테스트를 만드는 과정에서 발견됨), board-list.jsp 자체가 실제 임베디드
 * 톰캣+Jasper 위에서 정상 렌더링되는지는 GET 전용의 임시 테스트 컨트롤러로 우회 검증한다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.sql.init.mode=never")
class BoardListViewRenderTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @TestConfiguration
    static class TestViewControllerConfig {

        @Bean
        TestOnlyController testOnlyController() {
            return new TestOnlyController();
        }
    }

    @Controller
    static class TestOnlyController {

        @GetMapping("/__test-only/board-list")
        public String render(Model model) {
            CategoryResponseDto category = CategoryResponseDto.builder().id(1L).name("윤리학").build();

            BoardListResponseDto withAttachment = BoardListResponseDto.builder()
                    .seq(1L).categoryName("윤리학").author("소은").title("테스트 제목")
                    .viewCount(10).createdAt(LocalDateTime.of(2026, 6, 18, 9, 12))
                    .updatedAt(LocalDateTime.of(2026, 6, 19, 10, 3))
                    .hasAttachment(true)
                    .build();

            BoardListResponseDto withoutAttachment = BoardListResponseDto.builder()
                    .seq(2L).categoryName("철학사").author("재현").title("수정 이력 없는 글")
                    .viewCount(3).createdAt(LocalDateTime.of(2026, 6, 20, 14, 27))
                    .updatedAt(null)
                    .hasAttachment(false)
                    .build();

            model.addAttribute("categoryList", List.of(category));
            model.addAttribute("boardList", List.of(withAttachment, withoutAttachment));
            return "board-list";
        }
    }

    @Test
    @DisplayName("board-list.jsp가 실제 임베디드 톰캣 위에서 예외 없이 렌더링된다")
    void boardListJsp_rendersOnRealTomcat() {
        ResponseEntity<String> response = restTemplate.getForEntity("/__test-only/board-list", String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);

        String body = response.getBody();
        assertThat(body).contains("board-common.css");
        assertThat(body).contains("class=\"board-table\"");
        assertThat(body).contains("테스트 제목");
        assertThat(body).contains("2026.06.18 09:12");
        assertThat(body).contains("2026.06.19 10:03");
        assertThat(body).contains("수정 이력 없는 글");
        assertThat(body).contains("-");
        assertThat(body).contains("윤리학");
    }
}
