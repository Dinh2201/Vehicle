package com.example.vehicle.services;

import com.example.vehicle.dtos.request.vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.vehicle.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleCreationRequest request);

    List<VehicleResponse> getAllVehicles();

    VehicleResponse getVehicleById(Long id);

    VehicleResponse updateVehicle(Long id, VehicleUpdateRequest request);

    boolean deleteVehicle(List<Long> ids);
}
