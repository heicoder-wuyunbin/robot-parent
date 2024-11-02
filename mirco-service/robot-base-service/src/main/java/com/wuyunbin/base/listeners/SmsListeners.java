package com.wuyunbin.base.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuyunbin
 */
@Slf4j
@Component
public class SmsListeners {
    @RabbitListener(bindings = @QueueBinding(
            value=@Queue("sms"),
            exchange = @Exchange(name="test.direct"),
            key={"sms","both"}
    ))
    public void receive(String msg) {
        log.info("短信：{}",msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value=@Queue("email"),
            exchange = @Exchange(name="test.direct"),
            key={"email","both"}
    ))
    public void receive2(String msg) {
        System.out.println("邮件:" + msg);
    }
}
