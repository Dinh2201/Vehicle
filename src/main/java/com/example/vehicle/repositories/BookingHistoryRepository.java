package com.example.vehicle.repositories;

import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
    Optional<BookingHistory> findByDriver(Driver driver);

}
