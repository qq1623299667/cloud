package com.demo.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 石佳
 * @since 2020/06/11/19:53
 */
@Component
@Slf4j
public class KafkaReceiver {
    @KafkaListener(topics = {"key"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info(">>> record = {} <<<" + record);
            log.info(">>> message = {} <<<" + message);
        }
    }
}
