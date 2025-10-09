package com.example.vehicle.repositories;

import com.example.vehicle.entities.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
//    Page<Driver> findAll(Pageable pageable);
}