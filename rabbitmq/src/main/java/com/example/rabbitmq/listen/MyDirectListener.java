package com.example.rabbitmq.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Direct Exchange:进入key完全匹配的队列
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue("myDirectQueue"),
        exchange = @Exchange(value = "myDirectExchange", type = ExchangeTypes.DIRECT),
        key = "mine.direct"
))
@Slf4j
public class MyDirectListener {
    /**
     * listenerAdapter
     *
     * @param msg 消息内容,当只有一个参数的时候可以不加@Payload注解
     */
    @RabbitHandler
    public void onMessage(@Payload String msg) {
        log.info(">>> 监听到mq消息: {}  <<<",msg);
    }
}
