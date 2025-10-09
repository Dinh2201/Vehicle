package com.example.vehicle.messaging.impls;

import com.example.vehicle.messaging.DriverProducer;
import com.example.vehicle.properties.KafkaTopicProperties;
import com.example.vehicle.services.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverProducerImpl implements DriverProducer {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaService<String, Object> kafkaService;

    @Override
    public void producerDriverAction(Long driverId, String action, @Nullable Long bookingId) {
        var topic = kafkaTopicProperties.getBookingNoty();

        String formattedTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        Map<String, Object> message = new LinkedHashMap<>();
        try {

            if (bookingId != null) {
                message.put("bookingId", bookingId);
            }

            message.put("driverId", driverId);
            message.put("action", action);

            // ✅ Nếu là CANCEL thì mặc định là từ phía USER
            if ("CANCEL".equalsIgnoreCase(action)) {
                message.put("cancelledBy", "CANCELLED BY USER");
            }

            message.put("timestamp", formattedTime);

            log.info("[produceDriverAction] Producing driver action to topic {}: {}", topic, message);
            kafkaService.send(topic, message);

        } catch (Exception e) {
            throw new RuntimeException("Error producing Kafka message", e);
        }
    }
}
