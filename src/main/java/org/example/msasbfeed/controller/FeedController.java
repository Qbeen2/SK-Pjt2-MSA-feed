package org.example.msasbfeed.controller;

import org.example.msasbfeed.dto.FeedResponseDto;
import org.example.msasbfeed.service.FeedService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    // 해시태그 기반 추천 피드 (JSON 응답)
    @GetMapping(value = "/recommend/tag/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedResponseDto> getRecommendedFeedsByTag(@PathVariable String tag) {
        return feedService.getRecommendedFeedsByTag(tag);
    }

    // 그룹 기반 추천 피드 (JSON 응답)
    @GetMapping(value = "/recommend/group/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedResponseDto> getRecommendedFeedsByGroup(@PathVariable Long groupId) {
        return feedService.getRecommendedFeedsByGroup(groupId);
    }

    // 팔로우한 사용자 기반 추천 피드 (JSON 응답)
    @GetMapping(value = "/recommend/following/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedResponseDto> getRecommendedFeedsByFollowing(@PathVariable Long userId) {
        return feedService.getRecommendedFeedsByFollowing(userId);
    }
}
