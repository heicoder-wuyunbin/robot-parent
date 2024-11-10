package com.wuyunbin.apply;

import com.wuyunbin.sso.SSOClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {SSOClient.class})
@SpringBootApplication
public class RobotApplyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplyServiceApplication.class, args);
    }

}
