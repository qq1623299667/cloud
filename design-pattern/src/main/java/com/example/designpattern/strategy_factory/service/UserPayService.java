package com.example.designpattern.strategy_factory.service;

import java.math.BigDecimal;

public interface UserPayService {
    /**
     * 计算应付价格
     */
    public BigDecimal quote(BigDecimal orderPrice);
}
