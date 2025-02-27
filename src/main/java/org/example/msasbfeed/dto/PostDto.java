package org.example.msasbfeed.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDto {
    private Long pid;
    private Long uid;
    private String type;
    private Long gid;
    private LocalDateTime date;
    private String content;
    private Long youLike;
    private List<String> tag;
    private String image;
    private Integer commentCount;   // 댓글 개수

    @Builder
    public PostDto
            (Long pid, Long uid, String type, Long gid,
             LocalDateTime date, String content, Long youLike, List<String> tag, String image, Integer commentCount) {
        this.pid = pid;
        this.uid = uid;
        this.type = type;
        this.gid = gid;
        this.date = date;
        this.content = content;
        this.youLike = youLike;
        this.tag = tag;
        this.image = image;
        this.commentCount = commentCount;
    }
}