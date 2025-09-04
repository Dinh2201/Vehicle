package com.example.vehicle.repositories;

import com.example.vehicle.entities.vehicle.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    List<VehicleType> findAllByOrderByVehicleTypeIdAsc();

}
