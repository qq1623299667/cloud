package com.example.rabbitmq.entity;

import lombok.Data;

@Data
public class RabbitMessage {
    String myDirectExchange="myDirectExchange";
    String routingKey="mine.direct";
    String message="this is a direct message";
}
