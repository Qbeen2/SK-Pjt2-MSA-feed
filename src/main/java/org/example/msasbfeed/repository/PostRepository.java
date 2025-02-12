package org.example.msasbfeed.repository;

import org.example.msasbfeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 해시태그를 기준으로 게시글 조회
    @Query("SELECT p FROM Post p JOIN p.hashtags h WHERE h = :tag")
    List<Post> findByHashtag(@Param("tag") String tag);

    // 그룹 ID 기준으로 게시글 조회
    List<Post> findByGroupId(Long groupId);

    // 여러 작성자를 기준으로 게시글 조회 (팔로우한 사용자 기준)
    List<Post> findByUserIdIn(List<Long> userIds);
}
