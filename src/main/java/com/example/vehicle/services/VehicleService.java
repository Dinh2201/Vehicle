package com.example.vehicle.services;

import com.example.vehicle.dtos.request.Vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.Vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleCreationRequest request);

    List<VehicleResponse> getAllVehicles();

    VehicleResponse getVehicleById(Long id);

    VehicleResponse updateVehicle(Long id, VehicleUpdateRequest request);

    VehicleResponse updateVehicleLocation(Long id);

    boolean deleteVehicle(List<Long> ids);
}
