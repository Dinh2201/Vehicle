package com.example.vehicle.services;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverVehicleHistoryService {
    List<DriverVehicleHistoryResponse> getAllHistory(Pageable pageable);
}
