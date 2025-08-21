package com.example.vehicle.services;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;

public interface VehicleTypeService {
    VehicleTypeResponse create(VehicleTypeCreationRequest request);
}
