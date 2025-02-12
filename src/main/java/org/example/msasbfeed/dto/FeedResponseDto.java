package org.example.msasbfeed.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseDto {
    private Long id;
    private Long userId;
    private Long groupId;
    private String content;
    private LocalDateTime createdAt;
}
