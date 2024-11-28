package com.wuyunbin.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.wuyunbin.api.BaseClient;


@EnableFeignClients(clients = {BaseClient.class})
@SpringBootApplication
public class RobotMerchantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotMerchantServiceApplication.class, args);
    }

}
