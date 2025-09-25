package com.example.vehicle.services;

import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverService {
    DriverResponse createDriver(DriverRequest request);

    List<DriverResponse> getAllDrivers(Pageable pageable);

    DriverResponse getDriverById(Long id);

    DriverResponse updateDriver(Long id, DriverRequest request);

    boolean deleteDriver(List<Long> ids);

    boolean  acceptBooking(Long id, String action);
}
