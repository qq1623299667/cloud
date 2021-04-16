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
 * 首部交换机和扇形交换机都不需要路由键routingKey,交换机时通过Headers头部来将消息映射到队列的，
 * 有点像HTTP的Headers，Hash结构中要求携带一个键“x-match”，这个键的Value可以是any或者all，
 * 这代表消息携带的Hash是需要全部匹配(all)，还是仅匹配一个键(any)就可以了。相比直连交换机，
 * 首部交换机的优势是匹配的规则不被限定为字符串(string)而是Object类型。
 */
@Component
@Slf4j
public class MyHeadListener {
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "myHeadExchange",type = ExchangeTypes.HEADERS),
            value = @Queue("headQueue-one"),
            arguments = {
                    @Argument(name = "key-one",value = "1"),
                    @Argument(name = "key-two",value = "2"),
                    @Argument(name = "x-match",value = "any")
            }
    ))
    @RabbitHandler
    public void anyMatchOnMessage(@Payload String msg, @Headers Map<String,Object> headers){
        log.info(">>> 来自 {} 的消息 {} <<<",headers.get(AmqpHeaders.CONSUMER_QUEUE),msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "myHeadExchange",type = ExchangeTypes.HEADERS),
            value = @Queue("headQueue-two"),
            arguments = {
                    @Argument(name = "key-one",value = "1"),
                    @Argument(name = "x-match",value = "all")
            }
    ))
    @RabbitHandler
    public void allMatchOnMessage(@Payload String msg, @Headers Map<String,Object> headers){
        log.info(">>> 来自 {} 的消息 {} <<<",headers.get(AmqpHeaders.CONSUMER_QUEUE),msg);
    }
}
