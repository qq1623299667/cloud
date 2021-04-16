package com.example.rabbitmq.entity;

import lombok.Data;

@Data
public class RabbitHeaderMessage {
    String myDirectExchange="myDirectExchange";
    String routingKey="mine.direct";
}
