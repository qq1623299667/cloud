package com.example.rabbitmq.controller;

import com.example.rabbitmq.entity.RabbitHeaderMessage;
import com.example.rabbitmq.entity.RabbitMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rabbit")
@RestController
@Slf4j
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 访问localhost:8080/rabbit/test/exchange得到访问结果
     */
    @PostMapping("/test/exchange")
    public Object testExchange(@RequestBody RabbitMessage rabbitMessage){
        log.info(">>> 进入到MDC log方法 <<<");
        String myDirectExchange = rabbitMessage.getMyDirectExchange();
        String routingKey = rabbitMessage.getRoutingKey();
        String message = rabbitMessage.getMessage();
        rabbitTemplate.convertAndSend(myDirectExchange, routingKey, message);
        return message;
    }

    /**
     * 访问localhost:8080/rabbit/test/exchange得到访问结果
     */
    @PostMapping("/test/default/exchange")
    public Object testDefaultExchange(@RequestBody RabbitMessage rabbitMessage){
        String routingKey = rabbitMessage.getRoutingKey();
        String message = rabbitMessage.getMessage();
        rabbitTemplate.convertAndSend( routingKey, message);
        return message;
    }

    /**
     * 访问localhost:8080/rabbit/test/header/exchange得到访问结果
     */
    @PostMapping("/test/header/exchange")
    public Object testHeaderExchange(@RequestBody RabbitHeaderMessage rabbitHeaderMessage){
        String myDirectExchange = rabbitHeaderMessage.getMyDirectExchange();
        String routingKey = rabbitHeaderMessage.getRoutingKey();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("key-one", "1");
        Message message = new Message("hello".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(myDirectExchange, routingKey, message);
        return message;
    }

}
