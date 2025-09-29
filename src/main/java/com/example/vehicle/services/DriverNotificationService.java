package com.example.vehicle.services;

import com.example.vehicle.dtos.response.DriverNotificationResponse;
import com.example.vehicle.entities.DriverNotification;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DriverNotificationService {

    void notifyDriver(Long driverId, String message);
    List<DriverNotificationResponse> getDriverNotifications(Long id);
}
