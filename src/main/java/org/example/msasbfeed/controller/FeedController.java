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

    // 태그 기반 추천 피드 조회: 예) /feed/recommend/tag/coding
    @GetMapping(value = "/recommend/tag/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedResponseDto> getRecommendedFeedsByTag(@PathVariable String tag) {
        return feedService.getRecommendedFeedsByTag(tag);
    }

    // 그룹 기반 추천 피드 조회: 예) /feed/recommend/group/101
    @GetMapping(value = "/recommend/group/{gid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedResponseDto> getRecommendedFeedsByGroup(@PathVariable Long gid) {
        return feedService.getRecommendedFeedsByGroup(gid);
    }

    // 팔로우 기반 추천 피드 조회: 예) /feed/recommend/following/1
    @GetMapping(value = "/recommend/following/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedResponseDto> getRecommendedFeedsByFollowing(@PathVariable Long uid) {
        return feedService.getRecommendedFeedsByFollowing(uid);
    }
}
