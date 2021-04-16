package com.example.rabbitmq.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Receiver {
    /**
     * 访问:localhost:8080/rabbit/test/exchange方法
     * 设置参数为:{"myDirectExchange":"spring-boot-topic-exchange","routingKey":"foo.bar.a","message":32}
     * 结果为:>>> 监听到的消息是 32 <<<
     * @param msg
     */
    public void receiveMessage(String msg){
        log.info(">>> 监听到的消息是 {} <<<",msg);
    }
}
