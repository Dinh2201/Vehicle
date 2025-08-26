package com.example.vehicle.repositories;

import com.example.vehicle.entities.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Boolean existsByLicensePlate(String licensePlate);
}
