package com.example.vehicle.services.impls;

import com.example.vehicle.services.KafkaService;
import io.jsonwebtoken.io.SerializationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaServiceImpl<K, V> implements KafkaService{
    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topic, Object value) {
        this.send(topic, null, value);
    }

    @Override
    public void send(String topic, Object key, Object value) {
        try {
            kafkaTemplate.send(
                    MessageBuilder.withPayload(value)
                            .setHeader(KafkaHeaders.TOPIC, topic)
                            .setHeader(KafkaHeaders.KEY, key)
                            .build());
        } catch (SerializationException exception) {
            log.error("SerializationException exception: " + exception.getMessage(), exception);
        }
    }
}
