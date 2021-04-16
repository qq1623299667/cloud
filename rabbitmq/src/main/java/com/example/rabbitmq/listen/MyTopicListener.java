package com.example.rabbitmq.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * topic交换机:规则匹配,只要符合规则就监听
 * 通配符 # : 一个或多个word, * 一个word
 */
@Component
@Slf4j
public class MyTopicListener {
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "news-exchange",type = ExchangeTypes.TOPIC),
            value = @Queue("province-news-queue"),
            key = "province.#"
    ))
    @RabbitHandler
    public void provinceNews(String msg){
        log.info(">>> 来自省TV的消息 {} <<<",msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "news-exchange",type = ExchangeTypes.TOPIC),
            value = @Queue("city-news-queue"),
            key = "city.#"
    ))
    @RabbitHandler
    public void cityNews(String msg){
        log.info(">>> 来自市TV的消息 {} <<<",msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "news-exchange",type = ExchangeTypes.TOPIC),
            value = @Queue("street-news-queue"),
            key = "street.#"
    ))
    @RabbitHandler
    public void streetNews(String msg){
        log.info(">>> 来自街区TV的消息 {} <<<",msg);
    }
}
