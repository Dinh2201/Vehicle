package com.example.vehicle.repositories;

import com.example.vehicle.entities.DriverNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverNotificationRepository extends JpaRepository<DriverNotification, Long> {
    List<DriverNotification> findAllByDriver_DriverId(Long driverId);
}
