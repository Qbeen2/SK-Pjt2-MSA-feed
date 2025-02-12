package org.example.msasbfeed.service;

import org.example.msasbfeed.dto.FeedResponseDto;
import org.example.msasbfeed.entity.Post;
import org.example.msasbfeed.repository.PostRepository;
import org.example.msasbfeed.client.UserClient;  // 팔로우한 사용자 목록을 조회하기 위한 FeignClient
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private final PostRepository postRepository;
    private final UserClient userClient;

    public FeedService(PostRepository postRepository, UserClient userClient) {
        this.postRepository = postRepository;
        this.userClient = userClient;
    }

    // 해시태그 기반 추천: SQL에서 해당 해시태그를 가진 게시글 조회
    public List<FeedResponseDto> getRecommendedFeedsByTag(String tag) {
        List<Post> posts = postRepository.findByHashtag(tag);
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 그룹 기반 추천: SQL에서 특정 그룹에 속한 게시글 조회
    public List<FeedResponseDto> getRecommendedFeedsByGroup(Long groupId) {
        List<Post> posts = postRepository.findByGroupId(groupId);
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 팔로우 기반 추천: UserClient를 통해 팔로우한 사용자 ID 목록을 조회한 후 해당 작성자들의 게시글 조회
    public List<FeedResponseDto> getRecommendedFeedsByFollowing(Long userId) {
        List<Long> followingUserIds = userClient.getFollowingUserIds(userId);
        List<Post> posts = postRepository.findByUserIdIn(followingUserIds);
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private FeedResponseDto convertToDto(Post post) {
        return FeedResponseDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .groupId(post.getGroupId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
