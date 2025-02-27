package org.example.msasbfeed.service;

import org.example.msasbfeed.dto.FeedResponseDto;
import org.example.msasbfeed.client.UserClient;  // 팔로우한 사용자 목록 조회용 FeignClient
import org.example.msasbfeed.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class FeedService {

    private final UserClient userClient;


    @Autowired
    private WebClient.Builder webClientBuilder;

    public FeedService( UserClient userClient) {
        this.userClient = userClient;
    }

    // 태그 기반 추천: 해당 태그를 가진 게시글 조회
    // Mono 형식으로 반환
    public Mono<List<FeedResponseDto>>  getRecommendedFeedsByTag(String tag) {
        return webClientBuilder.baseUrl("http://localhost:8081").build() // 참조할 서비스
                .post() // Post 방식으로 진행
                .uri("/search/tag") // URI
                .contentType(MediaType.APPLICATION_JSON) // JSON 형태로 전달
                .bodyValue(Map.of("tag", tag)) // Map을 사용하여 JSON 변환 자동화
                .retrieve() // 전송
                .bodyToFlux(PostDto.class) // Flux로 여러 개의 데이터를 받음
                .collectList() // List로 형변환
                .map(posts -> posts.stream().map(this::convertToDto).toList()); // List로 변환
    }

//    // 그룹 기반 추천: 특정 그룹(gid)에 속한 게시글 조회
    public Mono<List<FeedResponseDto>>  getRecommendedFeedsByGroup(Long gid) {
        return webClientBuilder.baseUrl("http://localhost:8081").build()
                .post()
                .uri("/search/gid")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("gid", gid)) // Map을 사용하여 JSON 변환 자동화
                .retrieve()
                .bodyToFlux(PostDto.class) // Flux로 여러 개의 데이터를 받음
                .collectList()
                .map(posts -> posts.stream().map(this::convertToDto).toList());
        }
//
//    // 팔로우 기반 추천: userClient를 통해 팔로우한 사용자들의 uid 목록을 조회 후, 해당 사용자의 게시글 조회
//    public List<FeedResponseDto> getRecommendedFeedsByFollowing(Long uid) {
//        List<Long> followingIds = userClient.getFollowingUserIds(uid);
//        List<PostDto> posts = postRepository.findByUidIn(followingIds);
//        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
//    }

    private FeedResponseDto convertToDto(PostDto post) {
        return FeedResponseDto.builder()
                .pid(post.getPid())
                .uid(post.getUid())
                .type(post.getType())
                .gid(post.getGid())
                .date(post.getDate())
                .content(post.getContent())
                .youLike(post.getYouLike())
                .tags(post.getTags())
                .image(post.getImage())
                .build();
    }
}
