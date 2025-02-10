package org.example.msasbfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // <- Feign 활성화
public class MsaSbFeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaSbFeedApplication.class, args);
    }
}
