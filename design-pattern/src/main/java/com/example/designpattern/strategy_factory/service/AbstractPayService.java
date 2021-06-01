package com.example.designpattern.strategy_factory.service;

import com.example.designpattern.strategy_factory.factory.UserPayServiceStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.math.BigDecimal;

@Slf4j
public abstract class AbstractPayService implements UserPayService, InitializingBean {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        return new BigDecimal("1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String type = "PayService";
        String payService = this.getClass().getSimpleName().replace(type, "").toUpperCase();
        log.info("{} 策略工厂类 {} 开始注册",type,payService);
        UserPayServiceStrategyFactory.register(payService,this);
        log.info("{} 策略工厂类 {} 注册成功",type,payService);
    }
}
