package com.example.vehicle.services;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;

import java.util.List;

public interface DriverVehicleHistoryService {
    List<DriverVehicleHistoryResponse> getAllHistory();
}
