package com.interest.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.interest.message","com.interest.common"})
@EnableFeignClients(basePackages = "com.interest.common.feign")
public class InterestMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestMessageApplication.class, args);
    }

}
