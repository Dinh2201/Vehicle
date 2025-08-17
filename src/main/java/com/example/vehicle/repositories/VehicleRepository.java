package com.example.vehicle.repositories;

import com.example.vehicle.entities.vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {
    Optional<VehicleEntity> findById(String id);
}
