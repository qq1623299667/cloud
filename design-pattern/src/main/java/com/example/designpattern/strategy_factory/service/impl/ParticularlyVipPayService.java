package com.example.designpattern.strategy_factory.service.impl;

import com.example.designpattern.strategy_factory.service.AbstractPayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ParticularlyVipPayService extends AbstractPayService {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        if (orderPrice.compareTo(new BigDecimal("30"))>0) {
            return new BigDecimal("0.7");
        }
        return new BigDecimal("1");
    }

}
