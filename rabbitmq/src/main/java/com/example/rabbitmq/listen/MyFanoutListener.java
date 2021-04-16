package com.example.rabbitmq.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * FanoutExchange:每发一次消息,每个队列都会监听到exchange的广播
 */
@Component
@RabbitListeners({
        @RabbitListener(bindings = @QueueBinding(
                exchange = @Exchange(value = "myFanoutExchange",type = ExchangeTypes.FANOUT),
                value = @Queue("myFanoutQueue-one"),
                key = "key.one"
        )),
        @RabbitListener(bindings = @QueueBinding(
                exchange = @Exchange(value = "myFanoutExchange",type = ExchangeTypes.FANOUT),
                value = @Queue("myFanoutQueue-two"),
                key = "key.two"
        )),
})
@Slf4j
public class MyFanoutListener {
    @RabbitHandler
    public void onMessage(@Payload String msg, @Headers Map<String,Object> headers){
        log.info(">>> 来自 {} 的消息 {} <<<",headers.get(AmqpHeaders.CONSUMER_QUEUE),msg);
    }
}
