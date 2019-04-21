package com.interest.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.interest.auth","com.interest.common"})
public class InterestAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestAuthApplication.class, args);
    }

}
