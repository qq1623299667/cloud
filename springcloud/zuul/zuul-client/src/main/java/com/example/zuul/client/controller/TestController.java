package com.example.zuul.client.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * 访问localhost:5000/hello-service/test/zuul可以得到结果:{"status":500,"message":"a参数为空！"}
     * post访问http://localhost:5000/hello-service/test/zuul?a=1&b=2，可以得到结果：from zuul
     */
    @PostMapping("/zuul")
    public Object testZuul(){
        return "from zuul";
    }
}
