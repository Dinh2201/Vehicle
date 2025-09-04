package com.example.vehicle.repositories;

import com.example.vehicle.entities.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Boolean existsByLicensePlate(String licensePlate);

    @Query("SELECT v FROM vehicle v WHERE v.status = 'ACTIVE'")
    List<Vehicle> findActiveVehicles();

    List<Vehicle> findAllByOrderByVehicleIdAsc();
}
