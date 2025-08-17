package com.example.vehicle.repositories;

import com.example.vehicle.entities.vehicle.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, String> {}