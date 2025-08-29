package com.example.vehicle.services;

import com.example.vehicle.dtos.request.DispatchRequest;
import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;

import java.util.List;

public interface DriverService {
    DriverResponse createDriver(DriverCreationRequest request);

    List<DriverResponse> getAllDrivers();

    DriverResponse getDriverById(Long id);

    DriverResponse updateDriver(Long id, DriverCreationRequest request);

    boolean deleteDriver(List<Long> ids);

    boolean  acceptBooking(Long id, DispatchRequest request);
}
