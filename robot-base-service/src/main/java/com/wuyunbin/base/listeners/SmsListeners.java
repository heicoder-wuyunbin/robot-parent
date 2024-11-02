package com.wuyunbin.base.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuyunbin
 */
@Component
public class SmsListeners {
    @RabbitListener(queues = "demo")
    public void receive(String msg) {
        System.out.println("receive:" + msg);
    }
}
