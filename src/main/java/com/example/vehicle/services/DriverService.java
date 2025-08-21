package com.example.vehicle.services;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.entities.vehicle.Driver;

public interface DriverService {
    Driver createDriver(DriverCreationRequest request);
}
