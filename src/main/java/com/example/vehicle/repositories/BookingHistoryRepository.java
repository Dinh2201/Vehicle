package com.example.vehicle.repositories;

import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
    Optional<BookingHistory> findByDriver(Driver driver);
}
