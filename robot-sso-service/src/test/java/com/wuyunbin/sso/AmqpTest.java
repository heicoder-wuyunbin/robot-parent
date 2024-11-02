package com.wuyunbin.sso;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AmqpTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send(){
        String queueName="demo";
        String msg="hello world";
        rabbitTemplate.convertAndSend(queueName,msg);
    }
}
