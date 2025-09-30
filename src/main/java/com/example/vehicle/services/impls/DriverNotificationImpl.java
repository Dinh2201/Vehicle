package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.DriverNotificationResponse;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.DriverNotification;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.mappers.DriverNotificationMapper;
import com.example.vehicle.repositories.DriverNotificationRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.services.DriverNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverNotificationImpl implements DriverNotificationService {
    private final DriverNotificationRepository driverNotificationRepository;
    private final DriverRepository driverRepository;
    private final DriverNotificationMapper driverNotificationMapper;

    @Override
    public void notifyDriver(Long driverId, String message) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));

        log.info(">>> Gửi thông báo đến driver {}: {}", driver.getDriverId(), message);

    }


}
