package org.example.msasbfeed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user")
public interface UserClient {

    @GetMapping("/api/users/{uid}/following")
    List<Long> getFollowingUserIds(@PathVariable Long uid);
}
