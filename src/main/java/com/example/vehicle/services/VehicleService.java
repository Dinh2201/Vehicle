package com.example.vehicle.services;

import com.example.vehicle.dtos.request.Vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.Vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.VehicleCreationResponse;
import com.example.vehicle.entities.vehicle.Vehicle;

import java.util.List;

public interface VehicleService {
    VehicleCreationResponse createVehicle(VehicleCreationRequest request);

    List<VehicleCreationResponse> getAllVehicles();

    VehicleCreationResponse getVehicleById(Long id);

    VehicleCreationResponse updateVehicle(Long id, VehicleUpdateRequest request);

    boolean deleteVehicle(List<Long> ids);
}
