package com.example.designpattern.strategy_factory.service.impl;

import com.example.designpattern.strategy_factory.service.AbstractPayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VipPayService extends AbstractPayService {
    private boolean vipExpired = true;
    private int temporaryDiscount = 0;

    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        if(vipExpired && temporaryDiscount==0){
            temporaryDiscount++;
            return new BigDecimal("0.8");
        }
        return new BigDecimal("0.9");
    }
}
