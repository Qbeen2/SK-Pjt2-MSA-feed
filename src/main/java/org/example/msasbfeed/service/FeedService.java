package org.example.msasbfeed.service;

import org.example.msasbfeed.dto.FeedResponseDto;
import org.example.msasbfeed.client.PostClient;
import org.example.msasbfeed.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final PostClient postClient;
    private final UserClient userClient;

    // 추천 피드 조회 (해시태그 기반)
    public List<FeedResponseDto> getRecommendedFeeds(String tag) {
        return postClient.getPostsByTag(tag);
    }

    // 그룹 피드 조회
    public List<FeedResponseDto> getGroupFeeds(Long groupId) {
        return postClient.getPostsByGroup(groupId);
    }

    // 개인 피드 조회 (팔로우한 사람들의 게시글)
    public List<FeedResponseDto> getUserFeeds(Long userId) {
        List<Long> followingIds = userClient.getFollowingUserIds(userId);
        return postClient.getPostsByUsers(followingIds);
    }
}
