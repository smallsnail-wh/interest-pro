package com.interest.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.interest.bbs","com.interest.common"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.interest.common.feign")
public class InterestBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestBbsApplication.class, args);
    }

}
