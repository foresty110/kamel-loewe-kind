package com.kamel.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 컨트롤러와 무관한 순수 라우팅 설정을 담당한다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 루트 경로(/) 접속을 게시판 목록으로 리다이렉트한다.
     *
     * @param registry 뷰 컨트롤러 등록기
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/board");
    }
}
