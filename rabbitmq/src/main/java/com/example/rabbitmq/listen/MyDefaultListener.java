package com.example.rabbitmq.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认交换机其实就是直连交换机,可以理解为名称为空字符串的直连交换机,
 * 一个queue若不指定binding的交换机,就被绑定到默认交换机上,routingKey为queue的名称
 */
@Component
@Slf4j
@RabbitListener(queuesToDeclare = @Queue("myDefaultQueue"))
public class MyDefaultListener {

    @RabbitHandler
    public void onMessage(@Payload String msg, @Headers Map<String,Object> headers){
        log.info(">>> 来自 {} 的消息 {} <<<",headers.get(AmqpHeaders.CONSUMER_QUEUE),msg);
    }
}
