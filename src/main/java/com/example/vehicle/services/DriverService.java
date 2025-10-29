package com.example.vehicle.services;

import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.driver.DriverResponse;

import java.util.List;

public interface DriverService {
    ApiResponse<DriverResponse> createDriver(DriverRequest request);

    ApiResponse<List<DriverResponse>> getAllDrivers(int pageNo, int pageSize, String sortBy, String sortDir);

    ApiResponse<DriverResponse> getDriverById(Long id);

    ApiResponse<DriverResponse> updateDriver(Long id, DriverRequest request);

    ApiResponse<Boolean> deleteDriver(List<Long> ids);

    ApiResponse<Boolean>  handelBookingAction(Long id, String action, Long bookingId, boolean isFromDriver);
}
