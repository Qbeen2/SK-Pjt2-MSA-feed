package org.example.msasbfeed.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long groupId;   // 그룹 ID (없으면 NULL)

    @Column(columnDefinition = "TEXT")
    private String content; // 게시글 내용

    private LocalDateTime createdAt;

    // 해시태그 정보는 별도의 테이블(post_hashtags)로 관리 (리스트 형태)
    @ElementCollection
    @CollectionTable(name = "post_hashtags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "hashtag")
    private List<String> hashtags;
}
