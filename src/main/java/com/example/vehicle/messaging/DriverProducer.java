package com.example.vehicle.messaging;

public interface DriverProducer {
    void producerDriverAction(Long driverId, String action, Long bookingId, boolean isFromDriver);
}
