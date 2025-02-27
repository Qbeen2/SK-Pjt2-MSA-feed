package org.example.msasbfeed.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseDto {
    private Long pid;         // 게시글 번호
    private Long uid;         // 작성자 번호
    private String type;      // 게시글 타입
    private Long gid;         // 그룹 번호
    private LocalDateTime date; // 작성 날짜
    private String content;   // 게시글 내용
//    private Long cid;         // 댓글 번호
    private Long youLike;     // 좋아요 개수
    private List<String> tags; // 태그
    private String image;     // 이미지 URL
}
