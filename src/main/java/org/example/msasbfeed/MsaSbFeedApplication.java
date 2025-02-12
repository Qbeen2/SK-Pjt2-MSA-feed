package org.example.msasbfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.reactive.ReactiveManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(exclude = {
        ReactiveSecurityAutoConfiguration.class,
        ReactiveManagementWebSecurityAutoConfiguration.class
})
@EnableFeignClients  // <- Feign 활성화
public class MsaSbFeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaSbFeedApplication.class, args);
    }
}
