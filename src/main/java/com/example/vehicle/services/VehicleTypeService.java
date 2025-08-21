package com.example.vehicle.services;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.entities.vehicle.VehicleType;

public interface VehicleTypeService {
    VehicleType create(VehicleTypeCreationRequest request);
}
