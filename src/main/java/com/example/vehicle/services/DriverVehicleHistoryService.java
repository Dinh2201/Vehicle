package com.example.vehicle.services;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;

import java.util.List;

public interface DriverVehicleHistoryService {
    ApiResponse<List<DriverVehicleHistoryResponse>> getAllHistory(int pageNo, int pageSize, String sortBy, String sortDir);
}
