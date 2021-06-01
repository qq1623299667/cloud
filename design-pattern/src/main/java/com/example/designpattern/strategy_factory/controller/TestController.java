package com.example.designpattern.strategy_factory.controller;

import com.example.designpattern.strategy_factory.factory.UserPayServiceStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    // 控制台访问 http://localhost:8080/test/test1?type=VIP 即可查询结果
    // 利用策略+工厂+自动注册，实现代码的可扩展，复用，再多的策略都能很好的维护
    @GetMapping("/test1")
    public BigDecimal test1(@RequestParam(name = "type") String type){
        log.info("type={}",type);
        return UserPayServiceStrategyFactory.getByUserType(type).quote(new BigDecimal(100));
    }
}
