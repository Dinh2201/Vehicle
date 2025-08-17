package com.example.vehicle.services;

import com.example.vehicle.dtos.request.VehicleCreationRequest;
import com.example.vehicle.entities.vehicle.VehicleEntity;

public interface VehicleService {
    VehicleEntity createVehicle(VehicleCreationRequest request);
}
