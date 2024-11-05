package com.wuyunbin.sso;

import com.wuyunbin.member.api.MemberInfoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {MemberInfoClient.class})
@SpringBootApplication
public class RobotSsoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotSsoServiceApplication.class, args);
    }

}
