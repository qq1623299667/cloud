package com.demo.kafka.controller;

import com.demo.kafka.provider.KafkaSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 浏览器访问：http://localhost:8080/kafka/send，即可向kafka服务器发送消息
 * 通过kafka tool即可看到发送的消息
 * @author 石佳
 * @since 2020/06/11/20:23
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Resource
    private KafkaSender kafkaSender;

    @RequestMapping("/send")
    public String sendMessage(){
        kafkaSender.send();
        return "success";
    }
}
