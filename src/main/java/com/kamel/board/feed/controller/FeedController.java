package com.kamel.board.feed.controller;

import com.kamel.board.feed.dto.FeedPreviewResponseDto;
import com.kamel.board.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 피드 화면 렌더링을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService; // 피드 서비스

    /**
     * 피드 목록 화면을 조회한다.
     *
     * @param model 뷰로 전달할 데이터
     * @return 피드 목록 뷰 이름
     */
    @GetMapping("/feed")
    public String getList(Model model) {

        List<FeedPreviewResponseDto> feedList = feedService.getList();

        model.addAttribute("feedList", feedList);

        return "feed-list";
    }
}
