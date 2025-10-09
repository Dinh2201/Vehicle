package com.example.vehicle.services;

import com.example.vehicle.dtos.request.vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.vehicle.VehicleResponse;

import java.util.List;

public interface VehicleService {
    ApiResponse<VehicleResponse> createVehicle(VehicleCreationRequest request);

    ApiResponse<List<VehicleResponse>> getAllVehicles(int pageNo, int pageSize, String sortBy, String sortDir);

    ApiResponse<VehicleResponse> getVehicleById(Long id);

    ApiResponse<VehicleResponse> updateVehicle(Long id, VehicleUpdateRequest request);

    ApiResponse<Boolean> deleteVehicle(List<Long> ids);
}
