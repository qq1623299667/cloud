package com.example.designpattern.strategy_factory.service.impl;

import com.example.designpattern.strategy_factory.service.AbstractPayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SuperVipPayService extends AbstractPayService {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        return new BigDecimal("0.8");
    }
}
