package org.example.msasbfeed.repository;

import org.example.msasbfeed.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // 태그가 정확히 일치하는 게시글 조회
    @Query("SELECT p FROM PostEntity p WHERE p.tag = :tag")
    List<PostEntity> findByTag(@Param("tag") String tag);

    // 그룹 번호(gid) 기준 게시글 조회
    List<PostEntity> findByGid(Long gid);

    // 여러 사용자의(uid) 게시글 조회 (팔로우한 사용자 기준)
    List<PostEntity> findByUidIn(List<Long> uids);
}
