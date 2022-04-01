package com.computer.community_laundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CommunityLaundryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityLaundryApplication.class, args);
    }

}
