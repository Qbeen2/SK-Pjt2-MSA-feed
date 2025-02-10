package org.example.msasbfeed.controller;

import org.example.msasbfeed.service.FeedService;
import org.example.msasbfeed.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    // 추천 피드 조회 (해시태그 기반)
    @GetMapping("/recommend/{tag}")
    public List<FeedResponseDto> getRecommendedFeeds(@PathVariable String tag) {
        return feedService.getRecommendedFeeds(tag);
    }

    // 그룹 피드 조회
    @GetMapping("/group/{groupId}")
    public List<FeedResponseDto> getGroupFeeds(@PathVariable Long groupId) {
        return feedService.getGroupFeeds(groupId);
    }

    // 개인 피드 조회 (팔로우한 사람들의 게시글)
    @GetMapping("/user/{userId}")
    public List<FeedResponseDto> getUserFeeds(@PathVariable Long userId) {
        return feedService.getUserFeeds(userId);
    }
}
