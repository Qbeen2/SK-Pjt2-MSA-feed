package org.example.msasbfeed.service;

import org.example.msasbfeed.dto.FeedResponseDto;
import org.example.msasbfeed.entity.PostEntity;
import org.example.msasbfeed.repository.PostRepository;
import org.example.msasbfeed.client.UserClient;  // 팔로우한 사용자 목록 조회용 FeignClient
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

    // 태그 기반 추천: 해당 태그를 가진 게시글 조회
    public List<FeedResponseDto> getRecommendedFeedsByTag(String tag) {
        List<PostEntity> posts = postRepository.findByTag(tag);
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 그룹 기반 추천: 특정 그룹(gid)에 속한 게시글 조회
    public List<FeedResponseDto> getRecommendedFeedsByGroup(Long gid) {
        List<PostEntity> posts = postRepository.findByGid(gid);
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // 팔로우 기반 추천: userClient를 통해 팔로우한 사용자들의 uid 목록을 조회 후, 해당 사용자의 게시글 조회
    public List<FeedResponseDto> getRecommendedFeedsByFollowing(Long uid) {
        List<Long> followingIds = userClient.getFollowingUserIds(uid);
        List<PostEntity> posts = postRepository.findByUidIn(followingIds);
        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private FeedResponseDto convertToDto(PostEntity post) {
        return FeedResponseDto.builder()
                .pid(post.getPid())
                .uid(post.getUid())
                .type(post.getType())
                .gid(post.getGid())
                .date(post.getDate())
                .content(post.getContent())
                .cid(post.getCid())
                .youLike(post.getYouLike())
                .tag(post.getTag())
                .image(post.getImage())
                .build();
    }
}
