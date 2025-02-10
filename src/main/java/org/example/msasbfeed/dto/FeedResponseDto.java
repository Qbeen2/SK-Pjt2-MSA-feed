package org.example.msasbfeed.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedResponseDto {
    private Long id;
    private Long authorId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
