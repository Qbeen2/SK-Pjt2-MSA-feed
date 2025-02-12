package org.example.msasbfeed.client;

import org.example.msasbfeed.dto.FeedResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostClient {

    @GetMapping("/posts/tag/{tag}")
    List<FeedResponseDto> getPostsByTag(@PathVariable String tag);

    @GetMapping("/posts/group/{groupId}")
    List<FeedResponseDto> getPostsByGroup(@PathVariable Long groupId);

    @GetMapping("/posts/users")
    List<FeedResponseDto> getPostsByUsers(@RequestParam List<Long> userIds);
}
