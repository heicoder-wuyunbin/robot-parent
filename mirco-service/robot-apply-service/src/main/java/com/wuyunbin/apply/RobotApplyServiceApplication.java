package com.wuyunbin.apply;

import com.wuyunbin.merchant.MerchantClient;
import com.wuyunbin.sso.SSOClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wuyunbin
 */
@EnableFeignClients(clients = {SSOClient.class, MerchantClient.class})
@SpringBootApplication
public class RobotApplyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplyServiceApplication.class, args);
    }


}
